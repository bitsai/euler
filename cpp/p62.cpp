#include "euler.h"

using namespace std;

static map<vector<int>, long long> cubes;
static map<vector<int>, int> cube_permutations;

int main() {
  for( long long n = 0; true; n++ ) {
    long long cube = n * n * n;
	
    vector<int> cube_vec = number_to_int_vector( cube );

    sort( cube_vec.begin(), cube_vec.end() );
		
    map<vector<int>, long long>::iterator i = cubes.find( cube_vec );
		
    if( i == cubes.end() )
      cubes[ cube_vec ] = n * n * n;

    cube_permutations[ cube_vec ]++;
		
    if( cube_permutations[ cube_vec ] == 5 ) {
      cout << cubes[ cube_vec ] << endl;
      return 0;
    }
  }
}

