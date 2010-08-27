#include "euler.h"

using namespace std;

int main() {
  int n, count = 0;
	
  for( n = 2; count < 10001; n++ )
    if( is_prime( n ) )
      count++;
			
  cout << n - 1 << endl;
}

