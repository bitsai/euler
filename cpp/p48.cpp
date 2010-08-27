#include "euler.h"

using namespace std;

int main() {
  vector<int> sum;
	
  for( int i = 1; i <= 1000; i++ )
    sum = n_digit_add( sum, n_digit_pow( i, i, 10 ), 10 );
	
  for( vector<int>::reverse_iterator i = sum.rbegin(); i < sum.rend(); i++ )
    cout << *i;
		
  cout << endl;
}

