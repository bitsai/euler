#include "euler.h"

using namespace std;

int main() {
  for( int a = 1; a <= 998; a++ )
    for( int b = 2; b <= 999; b++ ) {
      int c = 1000 - b - a;
			
      if( a * a + b * b == c * c ) {
	cout << a * b * c << endl;
	return 0;
      }
    }
}

