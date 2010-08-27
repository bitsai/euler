#include "euler.h"

using namespace std;

int main() {
  int sum = 0;
  int sum_of_squares = 0;
	
  for( int i = 1; i <= 100; i++ ) {
    sum += i;
    sum_of_squares += i * i;
  }
	
  cout << ( sum * sum ) - sum_of_squares << endl;
}

