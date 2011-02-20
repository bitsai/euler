(ns p10
  (:use [utils :only (prime-sieve multiple? sum timed-test)])
  (:use [clojure.contrib.generic.math-functions :only (sqrt)]))

(defn solve [n]
  (let [prime-divisors (prime-sieve (sqrt n))
	not-prime? (fn [x] (some #(multiple? x %) prime-divisors))
	f (fn [nums] (sum (remove not-prime? nums)))]
    (+ (sum prime-divisors)
       (sum (pmap f [(range 3 999999 2)
		     (range 999999 2000000 2)])))))

(timed-test
 "Problem 10"
 142913828922
 (solve 2000000))
