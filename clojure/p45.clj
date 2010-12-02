(ns p45
  (:use [utils :only (s-gonal? triangle-nums timed-test)]))

(timed-test
 "Problem 45"
 1533776805
 (let [triple-gonal (filter
		     #(and (s-gonal? % 5) (s-gonal? % 6))
		     (triangle-nums))]
   (nth triple-gonal 2)))
