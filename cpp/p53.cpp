#include "euler.h"

using namespace std;

bool is_n_choose_k_greater_than_x( long n, long k, long x ) {
  double combinatorial = 1;

  for( int modifier = 0; modifier < k; modifier++ ) {
    combinatorial *= (double) ( n - modifier ) / ( k - modifier );
		
    if( combinatorial > x )
      return true;
  }
	
  return false;
}

int main() {
  int count = 0;

  for( int n = 1; n <= 100; n++ )
    for( int k = 1; k <= n; k++ )
      if( is_n_choose_k_greater_than_x( n, k, 1000000 ) )
	count++;
	
  cout << count << endl;
}

