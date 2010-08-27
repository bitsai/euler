#include "euler.h"

using namespace std;

int main() {
  int first = 0;
  int count = 0;
	
  for( int num = 0; count < 4; num++ )
    if( get_prime_factors( num ).size() == 4 ) {
      if( count == 0 )
	first = num;

      count++;
    }
    else {
      first = 0;
      count = 0;
    }
	
  cout << first << endl;
}

