#include "euler.h"

using namespace std;

struct Fraction {
  long long n;
  long long d;
};

bool equal( Fraction f, Fraction g ) {	
  return (double) f.n / f.d == (double) g.n / g.d;
}

bool is_curious_fraction( Fraction f ) {
  int n_tens_digit = f.n / 10;
  int n_ones_digit = f.n % 10;

  int d_tens_digit = f.d / 10;
  int d_ones_digit = f.d % 10;
	
  if( 	n_ones_digit == d_tens_digit &&
	equal( f, (Fraction) { n_tens_digit, d_ones_digit } ) )
    return true;

  return false;
}

Fraction mult( Fraction f, Fraction g ) {
  return (Fraction) { f.n * g.n, f.d * g.d };
}

Fraction reduce( Fraction f ) {
  vector<long long> divisors = get_divisors( f.n );

  for( vector<long long>::iterator i = divisors.begin(); i < divisors.end(); i++ )
    if( f.n % *i == 0 && f.d % *i == 0 ) {
      f.n /= *i;
      f.d /= *i;
    }
	
  return f;
}

int main() {
  Fraction product = { 1, 1 };
	
  for( int n = 10; n < 100; n++ )
    for( int d = n + 1; d < 100; d++ ) {
      Fraction f = { n, d };
			
      if( is_curious_fraction( f ) )
	product = mult( product, f );
    }
	
  cout << reduce( product ).d << endl;
}

