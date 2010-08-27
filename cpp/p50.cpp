#include "euler.h"

using namespace std;

int main() {
  vector<int> primes;
  int threshold = 1000000;
	
  int longest_length = 0;
  int longest_length_prime_sum = 0;
	
  for( int n = 2; accumulate( primes.begin(), primes.end(), 0 ) < threshold; n++ )
    if( is_prime( n ) )
      primes.push_back( n );
	
  for( vector<int>::iterator i = primes.begin(); i < primes.end(); i++ )
    for( vector<int>::iterator j = primes.end(); j > i; j-- ) {
      int sum = accumulate( i, j, 0 );
			
      if( j - i > longest_length && is_prime( sum )  ) {
	longest_length = j - i;
	longest_length_prime_sum = sum;
      }
    }
	
  cout << longest_length_prime_sum << endl;
}

