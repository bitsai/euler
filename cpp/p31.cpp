#include "euler.h"

using namespace std;

static int currencies[] = { 200, 100, 50, 20, 10, 5, 2, 1 };

int count_ways_to_make_change( int amount, int currency_index ) {
  int currency = currencies[ currency_index ];
  int ways = 0;
	
  for( int i = 0; i * currency <= amount; i++ ) {
    int remainder = amount - i * currency;
		
    if( currency_index + 1 < ( sizeof( currencies ) / sizeof( int ) ) )
      ways += count_ways_to_make_change( remainder, currency_index + 1 );
    else if( remainder == 0 )
      return 1;
  }
	
  return ways;
}

int main() {
  cout << count_ways_to_make_change( 200, 0 ) << endl;
}

