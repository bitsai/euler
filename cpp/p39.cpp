#include "euler.h"

using namespace std;

int count_solutions( int p ) {
  int count = 0;

  for( int a = 0; a < p / 2; a++ )
    for( int b = a; b < p / 2; b++ ) {
      int c = p - a - b;
			
      if( a * a + b * b == c * c ) 
	count++;
    }
	
  return count;
}

int main() {
  int max_count = -1;
  int max_p = -1;

  for( int p = 3; p <= 1000; p++ ) {
    int count = count_solutions( p );
	
    if( count > max_count ) {
      max_count = count;
      max_p = p;
    }
  }
	
  cout << max_p << endl;
}
