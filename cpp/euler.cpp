#include "euler.h"

using namespace std;

Quotient_Generator::Quotient_Generator() {
}

Quotient_Generator::Quotient_Generator( long long in_D ) {
  D = in_D;
  P = 0;
  Q = 1;
  a_0 = (long long) sqrt( D );
  a = a_0;
}

long long Quotient_Generator::next_quotient() {
  long long result = a;

  P = a * Q - P;
  Q = ( D - P * P ) / Q;
  a = (long long) ( a_0 + P ) / Q;
	
  return result;
}

Convergent_Generator::Convergent_Generator() {
}

Convergent_Generator::Convergent_Generator( Quotient_Generator in_qg ) {
  qg = in_qg;
  a_0 = qg.next_quotient();
  long long a_1 = qg.next_quotient();

  cur_p = Big_Integer( a_0 );
  next_p = Big_Integer( a_0 * a_1 + 1 );
	
  cur_q = Big_Integer( 1 );
  next_q = Big_Integer( a_1 );
}

Big_Fraction Convergent_Generator::next_convergent() {
  Big_Fraction result = (Big_Fraction) { cur_p, cur_q };
	
  long long a = qg.next_quotient();

  Big_Integer next_next_p = Big_Integer( a ) * next_p + cur_p;
  Big_Integer next_next_q = Big_Integer( a ) * next_q + cur_q;
	
  cur_p = next_p;
  next_p = next_next_p;
	
  cur_q = next_q;
  next_q = next_next_q;
	
  return result;
}

vector<long long> get_divisors( long long n ) {
  vector<long long> divisors;
	
  for( long long i = 1; i <= sqrt( n ); i++ )
    if( n % i == 0 ) {
      divisors.push_back( i );
			
      if( i != n / i )
	divisors.push_back( n / i );
    }
	
  return divisors;
}

long long get_factorial( long long n ) {
  return ( n == 0 ) ? 1 : n * get_factorial( n - 1 );
}

vector<long long> get_n_by_n_square_corners( long long n ) {
  vector<long long> corners;

  for( long long i = 0; i < 4; i++ ) {
    corners.push_back( n * n - i * ( n - 1 ) );
  }
	
  return corners;
}

string get_nth_permutation( string s, long long n ) {
  long long factorial = get_factorial( s.length() - 1 );

  if( n / s.length() >= factorial )
    return s;
	
  for( long long i = 0; i < s.length() - 1; i++ ) {
    long long temp_i = ( n / factorial ) % ( s.length() - i );
    string temp_s = s.substr( i + temp_i, 1 );

    for( long long j = i + temp_i; j > i; j-- ) 
      s.replace( j, 1, s.substr( j - 1, 1 ) );
			
    s.replace( i, 1, temp_s );
    factorial /= ( s.length() - ( i + 1 ) );
  }
	
  return s;
}

vector<long long> get_prime_factors( long long n ) {
  vector<long long> divisors = get_divisors( n );
  vector<long long>::iterator prime_end = remove_if( divisors.begin(), divisors.end(), is_composite );
  return vector<long long>( divisors.begin(), prime_end );
}

vector<long long> get_proper_divisors( int n ) {
  vector<long long> divisors = get_divisors( n );
  vector<long long>::iterator less_than_n_end = remove( divisors.begin(), divisors.end(), n );
  return vector<long long>( divisors.begin(), less_than_n_end );
}

long long get_s_gonal( long long p, long long s ) {
  return ( ( s - 2 ) * p * p - ( s - 4 ) * p ) / 2;
}

bool is_composite( long long n ) {
  return !is_prime( n );
}

bool is_integer( long double d ) {
  return d == (long double) (int) d;
}

bool is_palindrome( string s ) {
  for( long long i = 0; i < s.length() / 2; i++ )
    if( s[ i ] != s[ s.length() - 1 - i ] ) 
      return false;
	
  return true;
}

bool is_pandigital( string s ) {	
  return(	s.length() == 9 &&
		s.find( "1" ) != string::npos &&
		s.find( "2" ) != string::npos &&
		s.find( "3" ) != string::npos &&
		s.find( "4" ) != string::npos &&
		s.find( "5" ) != string::npos &&
		s.find( "6" ) != string::npos &&
		s.find( "7" ) != string::npos &&
		s.find( "8" ) != string::npos &&
		s.find( "9" ) != string::npos );
}

bool is_prime( long long n ) {
  if( n < 2 ) 
    return false;

  for( long long divisor = 2; divisor <= sqrt( n ); divisor++ )
    if( n % divisor == 0 ) 
      return false;

  return true;
}

bool is_s_gonal( long long p, long long s ) {
  double n = ( sqrt( 8 * ( s - 2 ) * p + ( s - 4 ) * ( s - 4 ) ) + s - 4 ) / ( 2 * ( s - 2 ) );
  return is_integer( n ) && n > 0;
}

string number_to_string( long long n ) {
  stringstream ss;
  ss << n;
  return ss.str();
}

long long string_to_number( string s ) {
  return atoll( s.c_str() );
}

vector<string> tokenize( string s, string delimiters ) {
  vector<string> tokens;

  string::size_type start_pos = s.find_first_not_of( delimiters, 0 );
  string::size_type end_pos = s.find_first_of( delimiters, start_pos );
	
  while( start_pos != string::npos || end_pos != string::npos ) {
    tokens.push_back( s.substr( start_pos, end_pos - start_pos ) );

    start_pos = s.find_first_not_of( delimiters, end_pos );
    end_pos = s.find_first_of( delimiters, start_pos );
  }
	
  return tokens;
}

