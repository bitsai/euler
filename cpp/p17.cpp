#include "euler.h"

using namespace std;

int convert_digit( int input ) {
  // "one", "two", "six"
  if( input == 1 || input == 2 || input == 6 ) 
    return 3;

  // "four", "five", "nine"
  if( input == 4 || input == 5 || input == 9 ) 
    return 4;
    
  // "three", "seven", "eight"
  if( input == 3 || input == 7 || input == 8 ) 
    return 5;

  return 0;
}

int convert_tens( int input ) {
  // "forty", "fifty", "sixty"
  if( input == 4 || input == 5 || input == 6 ) 
    return 5;
  
  // "twenty", "thirty", "eighty", "ninety"
  if( input == 2 || input == 3 || input == 8 || input == 9 ) 
    return 6;
  
  // "seventy"
  if( input == 7 ) 
    return 7;

  return 0;
}

int convert_teens( int input ) {
  // "ten"
  if( input == 0 ) 
    return 3;
  
  // "eleven", "twelve"
  if( input == 1 || input == 2 ) 
    return 6;
  
  // "fifteen", "sixteen"
  if( input == 5 || input == 6 ) 
    return 7;
  
  // "thirteen", "fourteen", "eighteen", "nineteen"
  if( input == 3 || input == 4 || input == 8 || input == 9 ) 
    return 8;
  
  // "seventeen"
  if( input == 7 ) 
    return 9;

  return 0;
}

int count_letters_in_num( int num ) {
  int total = 0;

  if( num == 1000 ) 
    return 11;

  int hundreds_digit = ( num % 1000 ) / 100;
  int tens_digit = ( num % 100 ) / 10;
  int ones_digit = num % 10;

  if( hundreds_digit > 0 ) {
    total = convert_digit( hundreds_digit ) + 7;

    if( tens_digit > 0 || ones_digit > 0 ) 
      total += 3;
  }

  if( tens_digit > 1 ) 
    total += convert_tens( tens_digit );
  else if( tens_digit == 1 ) 
    return total + convert_teens( ones_digit );

  if( ones_digit > 0 ) 
    total += convert_digit( ones_digit );

  return total;
}

int main() {
  int total = 0;

  for( int i = 1; i <= 1000; i++ ) 
    total += count_letters_in_num( i );

  cout << total << endl;
}
