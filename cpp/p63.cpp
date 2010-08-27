#include "euler.h"

using namespace std;

int main() {
  int sum = 0;

  for( int n = 1; n < 10; n++ ) {
    bool done = false;
	
    for( int k = 1; !done; k++ ) {
      vector<int> result = n_digit_pow( n, k, -1 );
			
      if( result.size() == k )
	sum++;
      else
	done = true;
    }
  }
	
  cout << sum << endl;
}

