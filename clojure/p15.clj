(ns p15
  (:use [utils :only (n-choose-k timed-test)]))

;; # paths in NxN grid = sum of squares of Nth row of Pascal's triangle
;; sum of squares of Nth row of Pascal's triangle = (2N choose N)
;; (http://en.wikipedia.org/wiki/Pascal_triangle#Rows)

(timed-test
 "Problem 15"
 137846528820
 (let [n 20]
   (n-choose-k (* 2 n) n)))
