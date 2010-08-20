(ns p21
  (:use [utils :only (proper-divisors sum timed-test)]))

(defn amicable? [a]
  (let [d #(sum (proper-divisors %))
	b (d a)]
    (and (= (d b) a)
	 (not= a b))))

(timed-test
 "Problem 21"
 31626
 (sum (filter amicable? (range 220 10000))))
