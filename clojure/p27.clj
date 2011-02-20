(ns p27
  (:use [utils :only (quadratic prime? prime-sieve timed-test)]))

(defn consecutive-primes [a b]
  (take-while #(prime? (quadratic % a b)) (range)))

(defn make-pairs [as]
  (for [a as
	b (prime-sieve 1000)]
    [(* a b) (count (consecutive-primes a b))]))

(timed-test
 "Problem 27"
 -59231
 (let [f (fn [pairs] (apply max-key second pairs))]
   (first (f (pmap f [(make-pairs (range -999 0 2))
		      (make-pairs (range 0 1000 2))])))))
