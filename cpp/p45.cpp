#include "euler.h"

using namespace std;

int main() {
  int n = 286;
	
  while( !is_s_gonal( get_s_gonal( n, 3 ), 5 ) || 
	 !is_s_gonal( get_s_gonal( n, 3 ), 6 ) )
    n++;

  cout << get_s_gonal( n, 3 ) << endl;
}

