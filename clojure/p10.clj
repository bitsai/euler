(ns p10
  (:use [utils :only (multiple? timed-test)])
  (:use [clojure.contrib.generic.math-functions :only (sqr sqrt)]))

(defn sieve [n]
  (loop [[p & _ :as nums] (cons 2 (range 3 (inc n) 2))
	 primes []]
    (cond
     (< n 2) []
     (> (sqr p) n) (concat primes nums)
     :else (recur (remove #(multiple? % p) nums)
		  (conj primes p)))))

(timed-test
 "Problem 10"
 142913828922
 (let [prime-divisors (sieve (sqrt 2000000))
       not-prime? (fn [x] (some #(multiple? x %) prime-divisors))
       f (fn [nums] (reduce + (remove not-prime? nums)))]
   (+ (reduce + prime-divisors)
      (reduce + (pmap f [(range 3 499999 2)
			 (range 499999 999999 2)
			 (range 999999 1499999 2)
			 (range 1499999 2000000 2)])))))
