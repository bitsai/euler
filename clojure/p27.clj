(ns p27
  (:use [euler :only (sqr prime? prime-sieve max-key timed-test)]))

(defn quadratic [n a b]
  (+ (sqr n) (* a n) b))

(defn count-consecutive-primes [[a b]]
  (count (take-while #(prime? (quadratic % a b)) (range))))

(timed-test
 -59231
 (let [pairs (for [a (range -999 1000 2)
                   b (prime-sieve 1000)]
               [a b])
       [a b] (apply max-key count-consecutive-primes pairs)]
   (* a b)))
