(ns p34
  (:use [utils :only (timed-test)]))

;; Only 4 factorions: 1, 2, 145, 40585.  As per the problem, 1 & 2 don't count.

(timed-test
 "Problem 34"
 40730
 (+ 145 40585))
