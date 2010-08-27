#include "euler.h"

using namespace std;

int main() {
  ifstream file( "../data/names.txt" );
  string line;
  vector<string> names;
  int total = 0;
	
  while( getline( file, line ) ) {
    vector<string> new_names = tokenize( line, ", \"" );
    names.insert( names.end(), new_names.begin(), new_names.end() );
  }

  sort( names.begin(), names.end() );
	
  for( int i = 0; i < names.size(); i++ ) {
    for( int j = 0; j < names[ i ].size(); j++ ) 
      total += ( names[ i ][ j ] - 64 ) * ( i + 1 );
  }
	
  cout << total << endl;
}
