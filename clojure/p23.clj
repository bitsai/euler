(ns p23
  (:use [euler :only (proper-divisors sum timed-test)]))

(defn abundant? [n]
  (> (sum (proper-divisors n)) n))

(timed-test
 "Problem 23"
 4179871
 (let [nums (range 1 20162)
       abundants (vec (filter abundant? nums))
       sums-of-abundants (set (for [i (range (count abundants))
				    j (range (inc i))]
				(+ (abundants i) (abundants j))))]
   (sum (remove sums-of-abundants nums))))
