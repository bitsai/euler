#include "euler.h"

using namespace std;

int main() {
  int largest = -1;

  for( int a = 1; a < 1000; a++ )
    for( int b = a; b < 1000; b++ ) {
      int product = a * b;
			
      if( product > largest && is_palindrome( number_to_string( product ) ) )
	largest = product;
    }
		
  cout << largest << endl;
}

