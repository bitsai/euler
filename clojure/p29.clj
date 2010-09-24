(ns p29
  (:use [utils :only (expt timed-test)]))

(timed-test
 "Problem 29"
 9183
 (let [nums (for [a (range 2 101)
		  b (range 2 101)]
	      (expt a b))]
   (count (distinct nums))))
