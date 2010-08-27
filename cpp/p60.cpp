#include "euler.h"

using namespace std;

static map<pair<int, int>, bool> concatenation_table;

bool concatenates_to_prime( int a, int b ) {
  pair<int, int> key = make_pair( a, b );
  map<pair<int, int>, bool>::iterator i = concatenation_table.find( key );
	
  if( i != concatenation_table.end() )
    return i->second;
		
  string a_str = number_to_string( a );
  string b_str = number_to_string( b );
		
  if( is_prime( string_to_number( a_str + b_str ) ) && 
      is_prime( string_to_number( b_str + a_str ) ) ) {	
    concatenation_table[ key ] = true;
    return true;
  } else {
    concatenation_table[ key ] = false;
    return false;
  }
}

bool concatenates_with_set( int n, vector<int> set ) {
  for( size_t i = 0; i < set.size(); i++ )
    if( !concatenates_to_prime( n, set[ i ] ) )
      return false;
	
  return true;
}

vector<int> grow_set_of_primes( vector<int> cur_set, vector<int> primes, int start ) {
  vector<int> largest_set = cur_set;

  for( int i = start; i >= 0; i-- ) {
    int candidate_prime = primes[ i ];
	
    if( concatenates_with_set( candidate_prime, cur_set ) ) {
      vector<int> new_set( cur_set );
      new_set.push_back( candidate_prime );
      vector<int> new_new_set = grow_set_of_primes( new_set, primes, i - 1 );		

      if( new_new_set.size() > largest_set.size() )
	largest_set = new_new_set;
    }
  }
	
  return largest_set;
}

int main() {
  int n = 2;
  vector<int> primes;
  vector<int> set_of_primes;
	
  while( set_of_primes.size() < 5 ) {
    if( is_prime( n ) ) {
      set_of_primes = grow_set_of_primes( vector<int>( 1, n ), primes, primes.size() - 1 );			
      primes.push_back( n );
    }
		
    n++;
  }
	
  cout << accumulate( set_of_primes.begin(), set_of_primes.end(), 0 ) << endl;
}

