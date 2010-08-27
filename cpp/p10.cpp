#include "euler.h"

using namespace std;

// http://en.wikipedia.org/wiki/Sieve_of_Eratosthenes#Euler.27s_Sieve
vector<long long> euler_sieve( long long n ) {
  vector<long long> nums;
	
  for( size_t i = 0; i <= n; i++ )
    nums.push_back( i );
	
  nums[ 0 ] = 0;
  nums[ 1 ] = 0;
	
  for( size_t i = 2; i <= sqrt( n ); i++ ) {	
    if( nums[ i ] == 0 )
      continue;
		
    for( size_t j = i * 2; j < nums.size(); j += i )
      nums[ j ] = 0;
  }
	
  vector<long long> primes = vector<long long>( nums.begin(), remove( nums.begin(), nums.end(), 0 ) );
	
  return primes;
}

int main() {
  vector<long long> primes = euler_sieve( 2000000 );
		
  cout << accumulate( primes.begin(), primes.end(), 0LL ) << endl;;
}

