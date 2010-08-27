#include "euler.h"

using namespace std;

bool is_leap_year( int year ) {
  // if the year is on a century, then only leap year if divisible by 400
  if( year % 100 == 0 ) 
    return ( year % 400 == 0 ) ? true : false;

  // for all other years, if the year is divisible by 4, it's a leap year
  return ( year % 4 == 0 ) ? true : false;
}

int get_days_in_month( int month, int year ) {
  // either 29 or 28 days in feb, depending on whether it's leap year
  if( month == 2 ) 
    return ( is_leap_year( year ) ) ? 29 : 28;
  // 30 days in april, june, september, november
  else if( month == 4 || month == 6 || month == 9 || month == 11 ) 
    return 30;
    
  // 31 days in the rest
  return 31;
}

int main() {
  int weekday = 2;
  int day = 1;
  int month = 1;
  int year = 1901;
  int total = 0;

  while( year < 2001 ) {
    // count sundays on first of the month
    if( weekday == 7 && day == 1 ) 
      total++;

    // increment weekday
    weekday++;

    if( weekday > 7 ) 
      weekday = 1;

    // increment day
    day++;

    // increment month    
    if( day > get_days_in_month( month, year ) ) {
      day = 1;
      month++;
    }

    // increment year
    if( month > 12 ) {
      month = 1;
      year++;
    }
  }

  cout << total << endl;
}
