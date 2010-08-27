#include "euler.h"

using namespace std;

int main() {
  ifstream file( "triangle.txt" );
  string line;
  vector< vector<int> > best_paths;

  while( getline( file, line ) ) {
    vector<string> cells = tokenize( line, " " );
    vector<int> row;
		
    for( int i = 0; i < cells.size(); i++ ) {
      int left_ancestor = 0, right_ancestor = 0;
			
      if( best_paths.size() > 0 && i > 0 )
	left_ancestor = best_paths.back()[ i - 1 ];
				
      if( best_paths.size() > 0 && best_paths.back().size() > i )
	right_ancestor = best_paths.back()[ i ];
				
      int best_path_thru_cell = max( left_ancestor, right_ancestor ) + string_to_number( cells[ i ] );
				
      row.push_back( best_path_thru_cell );
    }

    best_paths.push_back( row );
  }
	
  cout << *max_element( best_paths.back().begin(), best_paths.back().end() ) << endl;
}
