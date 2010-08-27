#include "euler.h"

using namespace std;

vector<int> make_group( const vector<int>& elements, int x, int y, int z ) {
  int arr[] = { elements[ x ], elements[ y ], elements[ z ] };
  return vector<int>( arr, arr + 3 );
}

vector< vector<int> > make_ring( const vector<int>& elements ) {
  vector< vector<int> > ring;
  ring.push_back( make_group( elements, 1, 0, 2 ) );
  ring.push_back( make_group( elements, 3, 2, 4 ) );
  ring.push_back( make_group( elements, 5, 4, 6 ) );
  ring.push_back( make_group( elements, 7, 6, 8 ) );
  ring.push_back( make_group( elements, 9, 8, 0 ) );
  return ring;
}

bool all_same_sum( const vector< vector<int> >& ring ) {
  int first_sum = accumulate( ring[ 0 ].begin(), ring[ 0 ].end(), 0 );

  for ( int i = 1; i != ring.size(); ++i )
    if ( first_sum != accumulate( ring[ i ].begin(), ring[ i ].end(), 0 ) )
      return false;

  return true;
}

bool is_canonical( const vector< vector<int> >& ring ) {
  for ( int i = 1; i != ring.size(); ++i )
    if ( ring[ 0 ][ 0 ] > ring[ i ][ 0 ] )
      return false;

  return true;
}

string make_str( const vector< vector<int> >& ring ) {
  stringstream os;

  for ( int i = 0; i != ring.size(); ++i )
    for ( int j = 0; j != ring[ i ].size(); ++j )
      os << ring[ i ][ j ];

  return os.str();
}

int main() {
  int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
  vector<int> elements( arr, arr + 10 );
  vector<string> strings;

  for ( int i = 0; i != get_factorial( elements.size() ); ++i ) {
    next_permutation( elements.begin(), elements.end() );
    vector< vector<int> > ring = make_ring( elements );
    
    if ( all_same_sum( ring ) && is_canonical( ring ) ) {
      string str = make_str( ring );
      if ( str.length() == 16 ) strings.push_back( str );
    }
  }

  cout << *max_element( strings.begin(), strings.end() ) << endl;
}
