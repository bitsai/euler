#include "euler.h"

using namespace std;

int main() {
  int largest = -1;

  for( int i = 1; i < 10000; i++ ) {
    stringstream ss;
    int n = 1;
		
    while( ss.str().length() < 9 ) {
      ss << i * n;
      n++;
    }
		
    int num = string_to_number( ss.str() );
		
    if(	is_pandigital( ss.str() ) &&
	num > largest ) 
      largest = num;
  }
	
  cout << largest << endl;
}

