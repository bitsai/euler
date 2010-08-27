#include "euler.h"

using namespace std;

vector<int> get_nth_row_in_pascal_triangle( int n ) {
  int r = n + 1, v = 1;
  vector<int> output( 1, v );
	
  for( int c = 1; c < r; c++ ) {
    v = v * ( r - c ) / c;
    output.push_back( v );
  }
	
  return output;
}

int main() {
  int size = 20;
  long long num_paths = 0;

  vector<int> row = get_nth_row_in_pascal_triangle( size );
	
  for( int i = 0; i < row.size(); i++ )
    num_paths += (long long) row [ i ] * row [ i ];
		
  cout << num_paths << endl;
}
