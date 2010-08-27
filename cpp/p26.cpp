#include "euler.h"

using namespace std;

int get_period( int a, int d, vector<int>& remainders ) {
  if( a == 0 ) 
    return 0;
	
  int remainder = ( a < d ) ? a : a % d;
	
  vector<int>::iterator i = find( remainders.begin(), remainders.end(), remainder );
	
  if( i != remainders.end() )
    return remainders.end() - i;
	
  remainders.push_back( remainder );

  return get_period( remainder * 10, d, remainders );
}

int main() {
  int d = -1;
  int longest_period = -1;

  for( int i = 1; i < 1000; i++ ) {
    vector<int> remainders;
    int period = get_period( 1, i, remainders );

    if( period > longest_period ) {
      d = i;
      longest_period = period;
    }
  }
	
  cout << d << endl;
}

