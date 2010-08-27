#include "euler.h"

using namespace std;

bool is_substring_divisible( string input ) {
  int primes[ 7 ] = { 2, 3, 5, 7, 11, 13, 17 };
	
  for( int i = 0; i < 7; i++ )
    if( string_to_number( input.substr( i + 1, 3 ) ) % primes[ i ] != 0 ) 
      return false;
	
  return true;
}

int main() {
  string num = "0123456789";
  long long sum = 0;
	
  for( int i = 0; i < get_factorial( num.length() ); i++ ) {
    string permutation = get_nth_permutation( num, i );

    if( is_substring_divisible( permutation ) ) 
      sum += string_to_number( permutation );
  }
	
  cout << sum << endl;
}

