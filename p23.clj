(ns p23
  (:use [utils :only (proper-divisors sum timed-test)]))

(timed-test
 "Problem 23"
 4179871
 (let [nums (range 1 20162)
       abundant? #(> (sum (proper-divisors %)) %)
       abundants (vec (filter abundant? nums))
       sums-of-abundants (set (for [x abundants
				    y abundants
				    :while (< (+ x y) 20162)]
				(+ x y)))]
   (- (sum nums) (sum sums-of-abundants))))
