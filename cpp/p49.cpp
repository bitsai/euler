#include "euler.h"

using namespace std;

vector<string> get_list_permutations( string s ) {
  vector<string> output;

  for( int i = 0; i < get_factorial( s.length() ); i++ ) 
    output.push_back( get_nth_permutation( s, i ) );
	
  sort( output.begin(), output.end() );
	
  return output;
}

int main() {
  for( int n = 1000; n < 10000; n++ ) {
    string n_s = number_to_string( n );
    vector<string> list = get_list_permutations( n_s );
    vector<string>::iterator it = find( list.begin(), list.end(), n_s );
	
    for( vector<string>::iterator i = it; i < list.end(); i++ ) {
      int second = string_to_number( *i );
			
      for( vector<string>::iterator j = i; j < list.end(); j++ ) {
	int third = string_to_number( *j );
			
	if(	n != 1487 &&
		second != n &&
		third - second == second - n &&
		is_prime( n ) &&
		is_prime( second ) &&
		is_prime( third ) ) {
	  cout << n << second << third << endl;
	  return 0;
	}
      }
    }
  }
}

