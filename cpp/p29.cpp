#include "euler.h"

using namespace std;

int main() {
  map<double, int> terms;
	
  for( int a = 2; a <= 100; a++ )
    for( int b = 2; b <= 100; b++ )
      terms[ pow( a, b ) ] = 1;
	
  cout << terms.size() << endl;
}
