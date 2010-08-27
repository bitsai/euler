#include "euler.h"

using namespace std;

int find_max_product( int nums[ 20 ][ 20 ] ) {
  int product;
  int max_product = -1;
	
  for( int row = 0; row < 20; row++ )
    for( int col = 0; col < 20; col++ ) {
      // Check vertical
      if( row < 17 ) {
	product = nums[ row ][ col ] * nums[ row + 1 ][ col ] * nums[ row + 2 ][ col ] * nums[ row + 3 ][ col ];
	if( product > max_product )
	  max_product = product;
      }
			
      // Check horizontal
      if( col < 17 ) {
	product = nums[ row ][ col ] * nums[ row ][ col + 1 ] * nums[ row ][ col + 2 ] * nums[ row ][ col + 3 ];
	if( product > max_product )
	  max_product = product;
      }
			
      // Check UL-to-LR diagonal
      if( row < 17 && col < 17 ) {
	product = nums[ row ][ col ] * nums[ row + 1 ][ col + 1 ] * nums[ row + 2 ][ col + 2 ] * nums[ row + 3 ][ col + 3 ];
	if( product > max_product )
	  max_product = product;
      }
			
      // Check UR-to-LL diagonal
      if( row < 17 && col > 2 ) {
	product = nums[ row ][ col ] * nums[ row + 1 ][ col - 1 ] * nums[ row + 2 ][ col - 2 ] * nums[ row + 3 ][ col - 3 ];
	if( product > max_product )
	  max_product = product;
      }
    } 

  return max_product;
}

int main() {
  ifstream file( "p11.txt" );
  string line;

  int nums[20][20];
  int row = 0;
	
  while( getline( file, line ) ) {
    vector<string> row_nums = tokenize( line, " " );
		
    for( int col = 0; col < row_nums.size(); col++ )
      nums[ row ][ col ] = string_to_number( row_nums[ col ] );
			
    row++;
  }
	
  cout << find_max_product( nums ) << endl;
}

