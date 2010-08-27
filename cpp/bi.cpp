#include "bi.h"

#include <iostream>
#include <sstream>

#include <math.h>
#include <stdlib.h>

using namespace std;

// Static helper functions

// Returns -1 if a < b, 0 if a = b, 1 if a > b
static int compare_digits( const vector<int>& a_digits, 
			   const vector<int>& b_digits ) {
  if( a_digits.size() < b_digits.size() )
    return -1;
  else if( a_digits.size() > b_digits.size() )
    return 1;
	
  for( int i = a_digits.size() - 1; i >= 0; i-- ) {
    if( a_digits[ i ] < b_digits[ i ] )
      return -1;
    else if ( a_digits[ i ] > b_digits[ i ] )
      return 1;
  }

  return 0;
}

static vector<int> add_digits( const vector<int>& a_digits, 
			       const vector<int>& b_digits ) {
  vector<int> result;
  size_t max_i = max( a_digits.size(), b_digits.size() );
  int carry = 0;
	
  for( size_t i = 0; i < max_i; i++ ) {
    int a_digit = ( i < a_digits.size() ) ? a_digits[ i ] : 0;
    int b_digit = ( i < b_digits.size() ) ? b_digits[ i ] : 0;
    int sum = a_digit + b_digit + carry;
    result.push_back( sum % 10 );
    carry = sum / 10;
  }
	
  if( carry > 0 )
    result.push_back( carry );

  return result;
}

// http://en.wikipedia.org/wiki/Subtraction_without_borrowing
static vector<int> subtract_digits( vector<int> minuend, 
				    vector<int> subtrahend ) {
  vector<int> result;
  vector<int> temp;

  // Pad subtrahend to have same number of digits as minuend.	
  for( int i = 0; i < minuend.size() - subtrahend.size(); i++ )
    subtrahend.push_back( 0 );
	
  // Find left-most digit in minuend greater than its counterpart in subtrahend
  int cutoff = minuend.size() - 1;
	
  while( cutoff >= 0 && minuend[ cutoff ] <= subtrahend[ cutoff ] )
    cutoff--;
	
  // Decrement that digit by 1
  minuend[ cutoff ]--;
	
  // Save all digits to the right as temp (discarding leading 0's)
  // then replace them with 9's
  for( int i = cutoff - 1; i >= 0; i-- ) {
    if( minuend[ i ] > 0 || temp.size() > 0 )
      temp.insert( temp.begin(), minuend[ i ] );

    minuend[ i ] = 9;
  }
	
  // Subtract subtrahend from new minuend, discarding leading 0's in the result
  for( int i = minuend.size() - 1; i >= 0; i-- ) {
    int diff = minuend[ i ] - subtrahend[ i ];
		
    if( diff > 0 || result.size() > 0 )
      result.insert( result.begin(), diff );
  }

  // Add result, digits saved in temp, and 1
  result = add_digits( result, temp );
  result = add_digits( result, vector<int>( 1, 1 ) );
	
  return result;
}

static vector<int> mult_digits( const vector<int>& a_digits, 
				const vector<int>& b_digits ) {
  vector<int> result;

  for( size_t b_i = 0; b_i < b_digits.size(); b_i++ ) {
    vector<int> row( b_i, 0 );
    int carry = 0;
	
    for( size_t a_i = 0; a_i < a_digits.size(); a_i++ ) {
      int a_digit = a_digits[ a_i ];
      int b_digit = b_digits[ b_i ];
      int product = a_digit * b_digit + carry;
      row.push_back( product % 10 );
      carry = product / 10;
    }
		
    if( carry > 0 )
      row.push_back( carry );
		
    result = add_digits( result, row );
  }
	
  return result;
}

// Public functions

Big_Integer::Big_Integer() {
  is_negative = false;
}

Big_Integer::Big_Integer( long long n ) {
  is_negative = n < 0 ? true : false;

  if( n == 0 ) {
    digits.push_back( 0 );
    return;
  }
	
  while( n != 0 ) {
    digits.push_back( (int) fabs( n % 10 ) );
    n /= 10;
  }
}

Big_Integer::Big_Integer( const string& s ) {
  int end = 0;

  if( s[ 0 ] == '-' ) {
    is_negative = true;
    end = 1;
  }

  for( int i = s.size() - 1; i >= end; i-- )
    digits.push_back( atoi( s.substr( i, 1 ).c_str() ) );
}

Big_Integer Big_Integer::power( long long exponent ) {
  Big_Integer base = *this;
  Big_Integer result( 1 );
	
  while( exponent > 0 ) {
    if( ( exponent & 1 ) == 1 ) 
      result = result * base;
			
    exponent >>= 1;
    base = base * base;
  }

  return result;
}

long long Big_Integer::to_number() const {
  long long n = 0;

  for( size_t i = 0; i < digits.size(); i++ )
    n += digits[ i ] * (long long int) pow( 10, i );
	
  return n;
}

string Big_Integer::to_string() const {
  string s = is_negative ? "-" : "";
	
  for( vector<int>::const_reverse_iterator i = digits.rbegin(); 
       i < digits.rend(); i++ ) {
    stringstream ss;
    ss << *i;
    s += ss.str();
  }
		
  return s;
}

vector<int> Big_Integer::get_digits() const {
  return digits;
}

// Friend functions

Big_Integer operator + ( const Big_Integer& a, const Big_Integer& b ) {
  Big_Integer result;
	
  if( a.is_negative == b.is_negative )
    result.is_negative = a.is_negative;
  else if( a.is_negative )
    return b - -a;
  else if( b.is_negative )
    return a - -b;
		
  result.digits = add_digits( a.digits, b.digits );

  return result;
}

Big_Integer operator - ( const Big_Integer& bi ) {
  Big_Integer result = bi;
	
  if( result.to_number() != 0 )
    result.is_negative = !result.is_negative;

  return result;
}

Big_Integer operator - ( const Big_Integer& a, const Big_Integer& b ) {
  Big_Integer result;
	
  if( b.is_negative )
    return a + -b;
	
  // b must be positive now
  if( a.is_negative )
    return -( -a + b );
	
  // both a & b must be positive now
  switch ( compare_digits( a.digits, b.digits ) ) {
  case -1:
    result.digits = subtract_digits( b.digits, a.digits );
    result.is_negative = true;
    break;
  case 0:
    return Big_Integer( 0 );
    break;
  case 1:
    result.digits = subtract_digits( a.digits, b.digits );
    break;
  }
	
  return result;
}

Big_Integer operator * ( const Big_Integer& a, const Big_Integer& b ) {
  Big_Integer result;

  result.is_negative = ( a.is_negative == b.is_negative ) ? false : true;
  result.digits = mult_digits( a.digits, b.digits );

  return result;
}

bool operator == ( const Big_Integer& a, const Big_Integer& b ) {
  return ( a.is_negative == b.is_negative && 
	   compare_digits( a.digits, b.digits ) == 0 );
}

bool operator != ( const Big_Integer& a, const Big_Integer& b ) {
  return !( a == b );
}

bool operator < ( const Big_Integer& a, const Big_Integer& b ) {
  if( a.is_negative && !b.is_negative)
    return true;
  if( !a.is_negative && !b.is_negative && 
      compare_digits( a.digits, b.digits ) == -1 )
    return true;
  if( a.is_negative && b.is_negative && 
      compare_digits( a.digits, b.digits ) == 1 )
    return true;
	
  return false;
}

ostream& operator << ( ostream& stream, const Big_Integer& bi ) {
  stream << bi.to_string();
  return stream;
}
