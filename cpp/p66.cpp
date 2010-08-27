#include "euler.h"

using namespace std;

// http://mathworld.wolfram.com/PellEquation.html
Big_Fraction solve_pell_equation( long long D ) {
  Quotient_Generator qg( D );
  Convergent_Generator cg( qg );
  Big_Fraction convergent = cg.next_convergent();

  while( convergent.n * convergent.n - ( convergent.d * convergent.d * D ) != 1 )
    convergent = cg.next_convergent();

  return convergent;
}

int main() {
  Big_Integer largest_x( -1 );
  int largest_D = -1;
	
  for( int D = 2; D <= 1000; D++ ) {
    if( !is_integer( sqrt( D ) ) ) {
      Big_Fraction f = solve_pell_equation( D );
		
      if( largest_x < f.n ) {
	largest_x = f.n;
	largest_D = D;
      }
    }
  }

  cout << largest_D << endl;
}

