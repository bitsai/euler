#include "euler.h"

using namespace std;

bool has_same_digits( int n1, int n2 ) {
  string n1_str = number_to_string( n1 );
  string n2_str = number_to_string( n2 );
	
  if( n1_str.size() != n2_str.size() )
    return false;

  for( int i = 0; i < n1_str.length(); i++ ) {
    size_t pos = n2_str.find( n1_str[ i ] );
		
    if( pos == string::npos )
      return false;
    else
      n2_str = n2_str.erase( pos, 1 );
  }
	
  return true;
}

int main() {
  int num;

  for( num = 1; !( has_same_digits( num, num * 2 ) &&
		   has_same_digits( num, num * 3 ) &&
		   has_same_digits( num, num * 4 ) &&
		   has_same_digits( num, num * 5 ) &&
		   has_same_digits( num, num * 6 ) ); num++ );
	
  cout << num << endl;
}

