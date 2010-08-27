#include "euler.h"

using namespace std;

static vector<int> fib_n_2_vec( 1, 0 );
static vector<int> fib_n_1_vec( 1, 1 );
static vector<int> fib_n_vec( 1, 1 );

vector<int> n_digit_fibonacci( size_t n ) {
  vector<int> output = fib_n_2_vec;
	
  fib_n_2_vec = fib_n_1_vec;
  fib_n_1_vec = fib_n_vec;
  fib_n_vec = n_digit_add( fib_n_2_vec, fib_n_1_vec, -1 );
	
  return output;
}

int main() {
  vector<int> cur_fib_vec = n_digit_fibonacci( -1 );
  int index = 0;
	
  while( cur_fib_vec.size() < 1000 ) {	
    cur_fib_vec = n_digit_fibonacci( -1 );
    index++;
  }
	
  cout << index << endl;
}

