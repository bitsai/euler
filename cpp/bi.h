#ifndef GUARD_bi_h
#define GUARD_bi_h

#include <string>
#include <vector>

class Big_Integer {
 private:
  bool is_negative;
  std::vector<int> digits;

 public:
  Big_Integer();
  Big_Integer( long long );
  Big_Integer( const std::string& s );
  Big_Integer power( long long exponent );
  long long to_number() const;
  std::string to_string() const;
  std::vector<int> get_digits() const;

  friend Big_Integer operator + ( const Big_Integer& a, const Big_Integer& b );
  friend Big_Integer operator - ( const Big_Integer& bi );
  friend Big_Integer operator - ( const Big_Integer& a, const Big_Integer& b );
  friend Big_Integer operator * ( const Big_Integer& a, const Big_Integer& b );
  friend bool operator == ( const Big_Integer& a, const Big_Integer& b );
  friend bool operator != ( const Big_Integer& a, const Big_Integer& b );
  friend bool operator < ( const Big_Integer& a, const Big_Integer& b );
  friend std::ostream& operator << ( std::ostream &stream, const Big_Integer& bi );
};

#endif

