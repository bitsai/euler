(ns p9
  (:use [utils :only (pythagorean? product timed-test)]))

(timed-test
 "Problem 9"
 31875000
 (let [total 1000
       triplets (for [a (range 1 (/ total 3))
		      b (range (inc a) (/ total 2))]
		  [a b (- total a b)])]
   (product (first (filter pythagorean? triplets)))))
