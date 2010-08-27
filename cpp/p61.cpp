#include "euler.h"

using namespace std;

bool is_valid_new_num( int n ) {
  return n >= 1000 && (	is_s_gonal( n, 3 ) ||
			is_s_gonal( n, 4 ) ||
			is_s_gonal( n, 5 ) ||
			is_s_gonal( n, 6 ) ||
			is_s_gonal( n, 7 ) || 
			is_s_gonal( n, 8 ) );
}

vector< vector<int> > add_num_to_set( int n, vector< vector<int> > set ) {
  // Row consists of a number and all its polygonal types
  vector<int> row( 1, n );

  for( int i = 3; i <= 8; i++ )
    if( is_s_gonal( n, i ) )
      row.push_back( i );
	
  set.push_back( row );
	
  return set;
}

bool is_valid_final_set( vector< vector<int> > set ) {
  vector<int> polygonal_types;
	
  // Get all polygonal types present
  for( size_t i = 0; i < set.size(); i++ )
    for( size_t j = 0; j < set[ i ].size(); j++ )
      polygonal_types.push_back( set[ i ][ j ] );
	
  // Make sure polygonals 3 thru 8 are present
  for( int i = 3; i <= 8; i++ )
    if( find( polygonal_types.begin(), polygonal_types.end(), i ) == polygonal_types.end() )
      return false;
	
  // If there are two nums with only 1 polygonal type, and they are the same type, fail
  for( size_t i = 0; i < set.size(); i++ )
    for( size_t j = i + 1; j < set.size(); j++ )
      if( 	set[ i ].size() == 2 && 
		set[ j ].size() == 2 && 
		set[ i ][ 1 ] == set[ j ][ 1 ] )
	return false;
	
  return true;
}

vector< vector<int> > find_largest_set( vector< vector<int> > cur_set ) {
  vector< vector<int> > largest_set = cur_set;

  int first_digits_of_first_num = cur_set.front()[ 0 ] / 100;
  int last_digits_of_last_num = cur_set.back()[ 0 ] % 100;

  if( cur_set.size() == 5 ) {
    int new_num = last_digits_of_last_num * 100 + first_digits_of_first_num;
		
    if( is_valid_new_num( new_num ) ) {
      vector< vector<int> > new_set = add_num_to_set( new_num, cur_set );

      if( is_valid_final_set( new_set ) )
	largest_set = new_set;
    }
  }
  else {		
    for( int i = 10; i < 100; i++ ) {
      int new_num = last_digits_of_last_num * 100 + i;
			
      if( is_valid_new_num( new_num ) ) {
	vector< vector<int> > new_set = add_num_to_set( new_num, cur_set );
	vector< vector<int> > new_largest_set = find_largest_set( new_set );
			
	if( new_largest_set.size() > largest_set.size() )
	  largest_set = new_largest_set;
      }
    }
  }
	
  return largest_set;
}

int main() {
  for( int n = 1000; n < 10000; n++ )
    if( is_s_gonal( n, 8 ) ) {
      vector< vector<int> > set = add_num_to_set( n, vector< vector<int> >() );
      vector< vector<int> > largest_set = find_largest_set( set );
			
      if( largest_set.size() == 6 ) {
	int sum = 0;
			
	for( size_t i = 0; i < largest_set.size(); i++ )
	  sum += largest_set[ i ][ 0 ];
				
	cout << sum << endl;
      }
    }
}

