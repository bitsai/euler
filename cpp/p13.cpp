#include "euler.h"

using namespace std;

int main() {
  ifstream file( "p13.txt" );
  string line;
  vector<int> sum;
	
  while( getline( file, line ) ) {
    sum = n_digit_add( sum, string_to_int_vector( line ), -1 );
  }

  for( int i = sum.size() - 1; i >= sum.size() - 10; i-- )
    cout << sum[ i ];
	
  cout << endl;
}

