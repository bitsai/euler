#include "euler.h"

using namespace std;

int main() {
  int largest_prime = -1;
	
  for( int n = 1; n < 10; n++ ) {
    stringstream ss;

    for( int i = 1; i <= n; i++ ) 
      ss << i;

    string s = ss.str();
		
    for( int j = 0; j < get_factorial( s.length() ); j++ ) {
      int permutation = string_to_number( get_nth_permutation( s, j ) );
			
      if( is_prime( permutation ) && permutation > largest_prime ) 
	largest_prime = permutation;
    }
  }
	
  cout << largest_prime << endl;
}
