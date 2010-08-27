#include "euler.h"

using namespace std;

int main() {
  int sum = 1;

  for( int n = 3; n <= 1001; n += 2 ) {
    vector<long long> corners = get_n_by_n_square_corners( n );
	
    sum += accumulate( corners.begin(), corners.end(), 0 );
  }
	
  cout << sum << endl;
}

