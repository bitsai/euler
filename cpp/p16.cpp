#include "euler.h"

using namespace std;

int main() {
  vector<int> digits = n_digit_pow( 2, 1000, -1 );
  cout << accumulate( digits.begin(), digits.end(), 0 ) << endl;
}

