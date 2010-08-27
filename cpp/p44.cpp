#include "euler.h"

using namespace std;

int p( int n ) {
  return n * (3 * n - 1) / 2;
}

bool is_pentagonal( int num, int& last_n, map<int, bool>& pentagonals ) {
  while( p( last_n ) < num ) {
    last_n++;
    pentagonals[ p( last_n ) ] = true;
  }
	
  return( pentagonals.find( num ) == pentagonals.end() ) ? false : true;
}

int main() {
  int last_n = 0;
  map<int, bool> pentagonals;
	
  for( int k = 2; true; k++ ) {
    int p_k = p( k );

    for( int j = k - 1; j > 0; j-- ) {
      int p_j = p( j );
		
      if( is_pentagonal( p_j + p_k, last_n, pentagonals ) && 
	  is_pentagonal( p_k - p_j, last_n, pentagonals ) ) {
	cout << p_k - p_j << endl;
	return 0;
      }
    }
  }
}

