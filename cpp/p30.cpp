#include "euler.h"

using namespace std;

int sum_of_5th_pow_of_digits( int n ) {
  int sum = 0;
  string s = number_to_string( n );
	
  for( size_t i = 0; i < s.length(); i++ )
    sum += pow( string_to_number( s.substr( i, 1 ) ), 5 );
	
  return sum;
}

int main() {
  int sum = 0;

  for( int i = 2; i <= pow( 9, 5 ) * 6; i++ )
    if( i == sum_of_5th_pow_of_digits( i ) )
      sum += i;
	
  cout << sum << endl;
}

