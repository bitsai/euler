#include "euler.h"

using namespace std;

int main() {
  vector<long long> prime_factors = get_prime_factors( 600851475143LL );
	
  cout << *max_element( prime_factors.begin(), prime_factors.end() ) << endl;
}
