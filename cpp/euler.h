#ifndef GUARD_euler_h
#define GUARD_euler_h

#include <algorithm>
#include <fstream>
#include <iostream>
#include <map>
#include <numeric>
#include <sstream>
#include <vector>

#include <math.h>

#include "bi.h"

struct Big_Fraction {
  Big_Integer n;
  Big_Integer d;
};

// http://mathworld.wolfram.com/PellEquation.html
class Quotient_Generator {
 private:
  long long D;
  long long P;
  long long Q;
  long long a_0;
  long long a;
		
 public:
  Quotient_Generator();
  Quotient_Generator( long long in_D );
  long long next_quotient();
};

// http://en.wikipedia.org/wiki/Continued_fraction#Infinite_continued_fractions
class Convergent_Generator {
 private:
  Quotient_Generator qg;
  long long a_0;
	
  Big_Integer cur_p;
  Big_Integer next_p;
	
  Big_Integer cur_q;
  Big_Integer next_q;
	
 public:
  Convergent_Generator();
  Convergent_Generator( Quotient_Generator in_qg );
  Big_Fraction next_convergent();
};

std::vector<long long> get_divisors( long long n );

long long get_factorial( long long n );

std::vector<long long> get_n_by_n_square_corners( long long n );

std::string get_nth_permutation( std::string s, long long n );

std::vector<long long> get_prime_factors( long long n );

std::vector<long long> get_proper_divisors( int n );

long long get_s_gonal( long long p, long long s );

bool is_composite( long long n );

bool is_integer( long double d );

bool is_palindrome( std::string s );

bool is_pandigital( std::string s );

bool is_prime( long long n );

bool is_s_gonal( long long p, long long s );

std::string number_to_string( long long n );

long long string_to_number( std::string s );

std::vector<std::string> tokenize( std::string s, std::string delimiters );

#endif

