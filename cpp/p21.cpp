#include "euler.h"

using namespace std;

int d( int n ) {
  vector<long long> proper_divisors = get_proper_divisors( n );	
  return accumulate( proper_divisors.begin(), proper_divisors.end(), 0 );
}

int main() {
  int sum = 0;

  for( int a = 1; a < 10000; a++ ) {
    int b = d( a );
		
    if( b > a && b < 10000 && d( b ) == a ) {
      sum += a;
      sum += b;
    }
  }
	
  cout << sum << endl;
}

