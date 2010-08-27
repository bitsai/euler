#include "euler.h"

using namespace std;

string rotate( string s ) {
  char c = s[ 0 ];
  s.erase( 0, 1 );
  s.push_back( c );

  return s;
}

bool is_circular_prime( int n ) {
  string n_string = number_to_string( n );

  for( int i = 0; i < n_string.size(); i++ ) {	
    if( !is_prime( string_to_number( n_string ) ) ) 
      return false;

    n_string = rotate( n_string );
  }
	
  return true;
}

int main() {
  int count = 0;

  for( int n = 0; n < 1000000; n++ )
    if( is_circular_prime( n ) ) 
      count++;
	
  cout << count << endl;
}
