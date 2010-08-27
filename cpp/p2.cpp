#include "euler.h"

using namespace std;

static int fib_n_2 = 0;
static int fib_n_1 = 1;
static int fib_n = 1;

long long fibonacci() {
  long long output = fib_n_2;
	
  fib_n_2 = fib_n_1;
  fib_n_1 = fib_n;
  fib_n = fib_n_2 + fib_n_1;
	
  return output;
}

int main() {
  int cur_fib = fibonacci();
  int sum = 0;
	
  while( cur_fib < 4000000 ) {
    if( cur_fib % 2 == 0 )
      sum += cur_fib;
			
    cur_fib = fibonacci();
  }
	
  cout << sum << endl;
}
