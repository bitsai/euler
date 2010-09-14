(ns p27
  (:use [utils :only (quadratic prime? natural-nums timed-test)])
  (:use [clojure.contrib.lazy-seqs :only (primes)]))

(defn prime-quadratic? [n a b] (prime? (quadratic n a b)))

(defn count-consecutive-primes [a b]
  (count (take-while #(prime-quadratic? % a b) (natural-nums))))

(timed-test
 "Problem 27"
 -59231
 (let [pairs (for [a (range -999 1000 2)
		   b (take-while #(< % 1000) primes)]
	       [(* a b) (count-consecutive-primes a b)])]
   (first (apply max-key second pairs))))
