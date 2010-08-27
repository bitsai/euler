#include "euler.h"

using namespace std;

int get_digital_sum( vector<int> vec ) {
  return accumulate( vec.begin(), vec.end(), 0 );
}

int main() {
  int max_sum = -1;

  for( int a = 1; a < 100; a++ )
    for( int b = 1; b < 100; b++ ) {
      int sum = get_digital_sum( n_digit_pow( a, b, -1 ) );
		
      if( sum > max_sum )
	max_sum = sum;
    }
	
  cout << max_sum << endl;
}

