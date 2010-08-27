#include "euler.h"

using namespace std;

int main() {
  int sum = 0;
  map<int, bool> pandigitals;
	
  for( int a = 1; a < 100; a++ )
    for( int b = 1; b < 10000; b++ ) {
      int product = a * b;
			
      stringstream ss;
      ss << a << b << product;
			
      if( is_pandigital( ss.str() ) && pandigitals.find( product ) == pandigitals.end() ) {
	pandigitals[ product ] = true;
	sum += product;
      }
    }
	
  cout << sum << endl;
}

