#include "euler.h"

using namespace std;

int main() {
  vector<int> product( 1, 1 );
	
  for( int i = 2; i <= 100; i++ )
    product = n_digit_mult( product, number_to_int_vector( i ), -1 );
		
  cout << accumulate( product.begin(), product.end(), 0 ) << endl;
}

