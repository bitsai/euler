#include "euler.h"

using namespace std;

vector<int> decrypt( vector<int> cipher_bytes, vector<int> key_bytes ) {
  vector<int> plain_bytes;
	
  for( size_t c_i = 0, k_i = 0; c_i < cipher_bytes.size(); c_i++, k_i = ( k_i + 1 ) % key_bytes.size() )
    plain_bytes.push_back( cipher_bytes[ c_i ] ^ key_bytes[ k_i ] );
	
  return plain_bytes;
}

string bytes_to_string( vector<int> bytes ) {
  string s;
	
  for( size_t i = 0; i < bytes.size(); i++ )
    s.push_back( (char) bytes[ i ] );
	
  return s;
}

bool has_common_english_words( string s ) {
  return( s.find( "the " ) != string::npos && 
	  s.find( "of " ) != string::npos && 
	  s.find( "to " ) != string::npos );
}

int main() {
  ifstream file( "cipher1.txt" );
  string line;
  vector<int> cipher_bytes;

  while( getline( file, line ) ) {
    vector<string> cipher_strings = tokenize( line, "," );
    transform( cipher_strings.begin(), cipher_strings.end(), back_inserter( cipher_bytes ), string_to_number );
  }
	
  for( int k1 = 97; k1 < 123; k1++ )
    for( int k2 = 97; k2 < 123; k2++ )
      for( int k3 = 97; k3 < 123; k3++ ) {
	vector<int> key_bytes;
				
	key_bytes.push_back( k1 );
	key_bytes.push_back( k2 );
	key_bytes.push_back( k3 );
			
	vector<int> plain_bytes = decrypt( cipher_bytes, key_bytes );
				
	if( has_common_english_words( bytes_to_string( plain_bytes ) ) )
	  cout << accumulate( plain_bytes.begin(), plain_bytes.end(), 0 ) << endl;
      }
}

