#include "euler.h"

using namespace std;

int main() {
  int n = 1;

  while( get_divisors( get_s_gonal( n, 3 ) ).size() <= 500 )
    n++;
	
  cout << get_s_gonal( n, 3 ) << endl;
}

