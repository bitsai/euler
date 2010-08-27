(ns p4
  (:use [utils :only (palindrome? timed-test)]))

(timed-test
 "Problem 4"
 906609
 (let [pals (for [x (range 100 1000)
		  y (range x 1000)
		  :when (palindrome? (* x y))]
	      (* x y))]
   (apply max pals)))
