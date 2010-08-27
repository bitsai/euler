#include "euler.h"

using namespace std;

bool is_truncatable_prime( int n ) {
  if( !is_prime( n ) ) 
    return false;

  string n_string = number_to_string( n );

  for( int i = 1; i < n_string.length(); i++ ) {
    string truncated_from_right = n_string.substr( 0, n_string.length() - i );
    string truncated_from_left = n_string.substr( i, n_string.length() - i );
		
    if(	is_prime( string_to_number( truncated_from_right ) ) == false ||
	is_prime( string_to_number( truncated_from_left ) ) == false ) 
      return false;
  }
	
  return true;
}

int main() {
  int count = 0;
  int sum = 0;
  int n = 11;
	
  while( count < 11 ) {
    if( is_truncatable_prime( n ) ) {
      count++;
      sum += n;
    }
		
    n++;
  }
	
  cout << sum << endl;
}

