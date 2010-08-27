#include "euler.h"

using namespace std;

// http://mathworld.wolfram.com/PellEquation.html
int get_continued_fraction_period( int D ) {
  if( is_integer( sqrt( D ) ) )
    return 0;

  int period = 0;
  Quotient_Generator qg( D );
  int a_0 = qg.next_quotient();
  int a = a_0;
	
  while( a != 2 * a_0 ) {		
    a = qg.next_quotient();
    period++;
  }
	
  return period;
}

int main() {
  int count = 0;

  for( int n = 1; n <= 10000; n++ ) {
    int period = get_continued_fraction_period( n );
		
    if( period != 0 && period % 2 == 1 )
      count++;
  }
	
  cout << count << endl;
}

