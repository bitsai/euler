#include "euler.h"

using namespace std;

int replace_digit( int num, int old_digit, int new_digit ) {
  vector<int> num_vec = number_to_int_vector( num );
	
  replace( num_vec.begin(), num_vec.end(), old_digit, new_digit );
	
  return int_vector_to_number( num_vec );
}

int get_prime_family_size( int num ) {
  int max_size = 1;
	
  if( is_prime( num ) == false )
    return 0;
	
  for( int old_digit = 0; old_digit < 10; old_digit++ ) {
    int size = 0;
	
    for( int new_digit = 0; new_digit < 10; new_digit++ ) {
      int new_num = replace_digit( num, old_digit, new_digit );

      if( new_num > num && is_prime( new_num ) )
	size++;
    }
		
    if( size > max_size ) 
      max_size = size;
  }
	
  return max_size;
}

int main() {
  int n;

  for( n = 0; get_prime_family_size( n ) < 7; n++ );
	
  cout << n << endl;
}

