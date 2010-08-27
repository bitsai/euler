#include "euler.h"

using namespace std;

string get_reverse_string( string s ) {
  return string( s.rbegin(), s.rend() );
}

long long reverse_and_add( long long n ) {
  return n + string_to_number( get_reverse_string( number_to_string( n ) ) );
}

bool is_lychrel( long long n ) {
  for( int i = 0; i <= 50; i++ ) {
    n = reverse_and_add( n );
		
    if( is_palindrome( number_to_string( n ) ) )
      return false;
  }
	
  return true;
}

int main() {
  int lychrel_count = 0;
	
  for( int i = 0; i < 10000; i++ )
    if( is_lychrel( i ) )
      lychrel_count++;
	
  cout << lychrel_count << endl;
}

