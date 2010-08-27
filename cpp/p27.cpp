#include "euler.h"

using namespace std;

int main() {
  int largest_n = 0;
  int product = 0;

  for( int a = -999; a < 1000; a++ )
    for( int b = -999; b < 1000; b++ ) {
      if( !is_prime( b ) ) 
	continue;

      int n = 0;

      do 
	n++;
      while( is_prime( n * n + a * n + b ) ) ;
			
      if( n > largest_n ) {
	largest_n = n;
	product = a * b;
      }
    }
	
  cout << product << endl;
}

