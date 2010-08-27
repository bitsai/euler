#include "euler.h"

#define LIMIT 20162 /* All integers greater than or equal to 20162 can be written as sum of 2 abundant numbers */

using namespace std;

bool is_abundant( int num ) {
  vector<long long> proper_divs = get_proper_divisors( num );

  int sum = accumulate( proper_divs.begin(), proper_divs.end(), 0 );

  return ( sum > num ) ? true : false;
}

int main() {
  vector<int> abundant_nums;
  bool is_sum_of_abundant_nums[ LIMIT ] = { false };
  int sum = 0;

  for( int i = 12; i < LIMIT; i++ )	
    if( is_abundant( i ) )
      abundant_nums.push_back( i );

  for( int i = 0; i < abundant_nums.size(); i++ )
    for( int j = i; j < abundant_nums.size(); j++ ) {
      int n = abundant_nums[ i ] + abundant_nums[ j ];
			
      if( n < LIMIT ) 
	is_sum_of_abundant_nums[ n ] = true;
    }
	
  for( int i = 0; i < LIMIT; i++ )
    if( is_sum_of_abundant_nums[ i ] == false )
      sum += i;
	
  cout << sum << endl;
}
