#include "euler.h"

using namespace std;

int get_sequence_length( long long n ) {
  if( n == 1 ) 
    return 1;

  long long new_n = ( n % 2 == 0 ) ? n / 2 : 3 * n + 1;
		
  return get_sequence_length( new_n ) + 1;
}

int main() {
  int max_length = -1;
  int max_i = -1;
	
  for( int i = 1; i < 1000000; i++ ) {
    int length = get_sequence_length( i );
		
    if( length > max_length ) {
      max_length = length;
      max_i = i;
    }
  }
	
  cout << max_i << endl;		
}
