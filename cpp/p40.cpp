#include "euler.h"

using namespace std;

int get_digit( int n ) {
  if( n < 1 )
    return 0;

  vector<int> digits;
  int integer = 1;
	
  while( digits.size() < n ) {
    string s = number_to_string( integer );

    for( int i = 0; i < s.length(); i++ ) 
      digits.push_back( string_to_number( s.substr( i, 1 ) ) );
			
    integer++;
  }
	
  return digits[ n - 1 ];
}

int main() {
  int product = 1;

  for( int i = 1; i <= 1000000; i *= 10 ) 
    product *= get_digit( i );
		
  cout << product << endl;
}

