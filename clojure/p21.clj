(ns p21
  (:use [euler :only (proper-divisors sum sum-if timed-test)]))

(defn d [n]
  (sum (proper-divisors n)))

(defn amicable? [a]
  (let [b (d a)]
    (and (= (d b) a) (not= a b))))

(timed-test
 "Problem 21"
 31626
 (sum-if amicable? (range 220 10000)))
