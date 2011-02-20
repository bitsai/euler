(ns p70
  (:use [utils :only (prime-sieve timed-test)]))

(defn n [[p1 p2]]
  (* p1 p2))

(defn phi [[p1 p2]]
  (* (dec p1) (dec p2)))

(defn permutation? [x y]
  (let [sorted-str #(sort (str %))]
    (= (sorted-str x) (sorted-str y))))

(defn phi-is-permutation-of-n? [pair]
  (permutation? (phi pair) (n pair)))

(defn ratio [pair]
  (/ (n pair) (phi pair)))

(timed-test
 "Problem 70"
 8319823
 (let [primes (prime-sieve 5000)
       pairs (for [p1 primes
		   p2 primes
		   :when (< (* p1 p2) 10000000)]
	       [p1 p2])
       permutation-pairs (filter phi-is-permutation-of-n? pairs)]
   (n (apply min-key ratio permutation-pairs))))
