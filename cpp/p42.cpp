#include "euler.h"

using namespace std;

bool is_triangle_word( string word ) {
  int word_value = 0;
  int t_n = 0;
	
  for( int i = 0; i < word.length(); i++ )
    word_value += word[ i ] - 64;
	
  for( int n = 0; t_n < word_value; n++ ) {
    t_n = 0.5 * n * ( n + 1 );
  }

  return ( t_n == word_value ) ? true : false;
}

int main() {
  ifstream file( "words.txt" );
  string line;
  vector<string> words;
  int triangle_words_count = 0;
	
  while( getline( file, line ) ) {
    vector<string> new_words = tokenize( line, ", \"" );
    words.insert( words.begin(), new_words.begin(), new_words.end() );
  }
	
  for( int i = 0; i < words.size(); i++ )
    if( is_triangle_word( words[ i ] ) ) 
      triangle_words_count++;
	
  cout << triangle_words_count << endl;
}

