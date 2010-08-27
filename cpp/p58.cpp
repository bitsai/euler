#include "euler.h"

using namespace std;

int main() {
  int n = 3;
  int num_primes = 3;
	
  while( (double) num_primes / ( n + n - 1 ) >= 0.1 ) {
    n += 2;
		
    vector<long long> corners = get_n_by_n_square_corners( n );
		
    for( vector<long long>::iterator i = corners.begin(); i < corners.end(); i++ )
      if( is_prime( *i ) )
	num_primes++;
  }
	
  cout << n << endl;
}

