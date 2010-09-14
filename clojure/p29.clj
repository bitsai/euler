(ns p29
  (:use [utils :only (timed-test)])
  (:use [clojure.contrib.math :only (expt)]))

(timed-test
 "Problem 29"
 9183
 (let [nums (for [a (range 2 101)
		  b (range 2 101)]
	      (expt a b))]
   (count (distinct nums))))
