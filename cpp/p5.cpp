#include "euler.h"

using namespace std;

bool divisible_by_all( int n ) {
  for( int i = 1; i <= 20; i++ )
    if( n % i != 0 )
      return false;
			
  return true;
}

int main() {
  int i;

  for( i = 1; !divisible_by_all( i ); i++ );
	
  cout << i << endl;
}

