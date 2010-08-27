#include "euler.h"

using namespace std;

int main() {
  int count = 0;

  Convergent_Generator cg( Quotient_Generator( 2 ) );
  Big_Fraction convergent = cg.next_convergent();
	
  for( int i = 0; i <= 1000; i++, convergent = cg.next_convergent() ) {
    Big_Integer numerator = convergent.n;
    Big_Integer denominator = convergent.d;
	
    if( numerator.get_digits().size() > denominator.get_digits().size() )
      count++;
  }
	
  cout << count << endl;
}

