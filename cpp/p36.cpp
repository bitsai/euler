#include "euler.h"

using namespace std;

string get_base_2_string_reversed( int n ) {
  stringstream ss;
	
  while( n > 0 ) {
    ( n & 1 == 1 ) ? ss << "1" : ss << "0";
    n >>= 1;
  }
	
  return ss.str();
}

int main() {
  int sum = 0;
	
  for( int i = 0; i < 1000000; i++ )
    if(	is_palindrome( number_to_string( i ) ) &&
	is_palindrome( get_base_2_string_reversed( i ) ) ) 
      sum += i;
	
  cout << sum << endl;
}
