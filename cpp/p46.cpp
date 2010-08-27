#include "euler.h"

using namespace std;

void make_more_primes( int n, vector<int>& primes ) {
  if( primes.size() == 0 ) 
    primes.push_back( 2 );

  for( int num = primes.back() + 1; primes.back() < n; num++ )
    if( is_prime( num ) ) 
      primes.push_back( num );
}

bool is_sum_of_prime_and_2_squares( int n, vector<int>& primes ) {
  make_more_primes( n, primes );
	
  for( int i = 0; primes[ i ] < n; i++ ) {
    int j = 1;
	
    while( primes[ i ] + 2 * ( j * j ) < n ) 
      j++;

    if( primes[ i ] + 2 * ( j * j ) == n )
      return true;
  }

  return false;
}

int main() {
  int num = 9;
  vector<int> primes;
	
  while( !( num % 2 != 0 && !is_prime( num ) && !is_sum_of_prime_and_2_squares( num, primes ) ) )		
    num++;
	
  cout << num << endl;
}

