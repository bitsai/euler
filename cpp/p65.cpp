#include "euler.h"

using namespace std;

int main() {
  // Build list of a's
  vector<int> as( 1, 2 );
	
  for( int k = 1; as.size() < 100; k++ ) {
    as.push_back( 1 );
    as.push_back( 2 * k );
    as.push_back( 1 );
  }
	
  // Calculate 100th h according to formula given in http://en.wikipedia.org/wiki/Continued_fraction#Infinite_continued_fractions
  Big_Integer h_2( 2 );
  Big_Integer h_1( 3 );
  Big_Integer h;

  for( int n = 2; n < 100; n++ ) {
    h = Big_Integer( as[ n ] ) * h_1 + h_2;
    h_2 = h_1;
    h_1 = h;
  }
	
  vector<int> h_digits = h.get_digits();
	
  cout << accumulate( h_digits.begin(), h_digits.end(), 0 ) << endl;
}

