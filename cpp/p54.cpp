#include "euler.h"

using namespace std;

struct Card_group_counts {
  int pairs_count;
  int trips_count;
  int quads_count;
};

int get_value( string card ) {
  if( card[ 0 ] == 'T' )
    return 10;
  if( card[ 0 ] == 'J' )
    return 11;
  if( card[ 0 ] == 'Q' )
    return 12;
  if( card[ 0 ] == 'K' )
    return 13;
  if( card[ 0 ] == 'A' )
    return 14;

  return string_to_number( card.substr( 0, 1 ) );
}

string get_suit(string card) {
  return card.substr(1, 1);
}

bool is_all_consecutive_values( vector<string> cards ) {
  for( size_t i = 0; i < cards.size() - 1; i++ )
    if( get_value( cards[ i ] ) != get_value( cards[ i + 1 ] ) - 1 )
      return false;
	
  return true;
}

bool is_all_same_suit( vector<string> cards ) {
  for( size_t i = 0; i < cards.size() - 1; i++ )
    if( get_suit( cards[ i ] ) != get_suit( cards[ i + 1 ] ) )
      return false;
	
  return true;
}

bool compare_cards( string card1, string card2 ) {
  return get_value( card1 ) < get_value( card2 );
}

vector< pair<int, int> > get_card_values_by_freq( vector<string> cards ) {
  map<int, int> freq;
  vector< pair<int, int> > output;
	
  for( vector<string>::iterator iter = cards.begin(); iter != cards.end(); iter++ )
    freq[ get_value( *iter ) ]++;

  for( map<int, int>::iterator iter = freq.begin(); iter != freq.end(); iter++ )
    output.push_back( make_pair( iter->second, iter->first ) );

  sort( output.begin(), output.end() );
	
  return output;
}

Card_group_counts get_card_group_counts( vector< pair<int, int> > card_values_by_freq ) {
  Card_group_counts result;
	
  result.pairs_count = 0;
  result.trips_count = 0;
  result.quads_count = 0;
	
  for( vector< pair<int, int> >::iterator iter = card_values_by_freq.begin(); iter != card_values_by_freq.end(); iter++ ) {
    if( iter->first == 2 )
      result.pairs_count++;
    if( iter->first == 3 )
      result.trips_count++;
    if( iter->first == 4 )
      result.quads_count++;
  }
	
  return result;
}

int get_hand_value( vector<string> cards, vector< pair<int, int> > card_values_by_freq ) {
  sort( cards.begin(), cards.end(), compare_cards );

  Card_group_counts card_group_counts = get_card_group_counts( card_values_by_freq );

  /* Royal Flush */
  if( is_all_consecutive_values( cards ) && is_all_same_suit( cards ) && get_value( cards[ 4 ] ) == 14 )
    return 10;
  /* Straight Flush */
  if( is_all_consecutive_values( cards ) && is_all_same_suit( cards ) )
    return 9;
  /* Four of a Kind */
  if( card_group_counts.quads_count == 1 )
    return 8;
  /* Full House */
  if( card_group_counts.trips_count == 1 && card_group_counts.pairs_count == 1 )
    return 7;
  /* Flush */
  if( is_all_same_suit( cards ) )
    return 6;
  /* Straight */
  if( is_all_consecutive_values( cards ) )
    return 5;
  /* Three of a Kind */
  if( card_group_counts.trips_count == 1 )
    return 4;
  /* Two Pairs */
  if( card_group_counts.pairs_count == 2 )
    return 3;
  /* One Pair */
  if( card_group_counts.pairs_count == 1 )
    return 2;
		
  return 1;
}

int get_winner( vector<string> p1_cards, vector<string> p2_cards ) {
  vector< pair<int, int> > p1_card_values_by_freq = get_card_values_by_freq( p1_cards );
  vector< pair<int, int> > p2_card_values_by_freq = get_card_values_by_freq( p2_cards );

  int p1_hand_value = get_hand_value( p1_cards, p1_card_values_by_freq );
  int p2_hand_value = get_hand_value( p2_cards, p2_card_values_by_freq );

  if( p1_hand_value > p2_hand_value )
    return 1;
  if( p1_hand_value < p2_hand_value )
    return 2;
		
  /* Compare remaining card values, from most frequent to least frequent */
  for( int i = p1_card_values_by_freq.size() - 1; i >= 0; i-- ) {
    int p1_card_value = p1_card_values_by_freq[ i ].second;
    int p2_card_value = p2_card_values_by_freq[ i ].second;
		
    if( p1_card_value > p2_card_value )
      return 1;
    if( p1_card_value < p2_card_value )
      return 2;
  }

  return 0;
}

int main() {
  ifstream file( "poker.txt" );
  string line;
  int p1_wins = 0;

  while( getline( file, line ) ) {
    vector<string> p1_cards;
    vector<string> p2_cards;
    vector<string> cards = tokenize( line, " " );

    p1_cards.insert( p1_cards.begin(), cards.begin(), cards.begin() + 5 );
    p2_cards.insert( p2_cards.begin(), cards.begin() + 5, cards.end() );
		
    if( get_winner( p1_cards, p2_cards ) == 1 )
      p1_wins++;
  }
	
  cout << p1_wins << endl;
}

