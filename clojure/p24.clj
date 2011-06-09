(ns p24
  (:use [euler :only (permutations timed-test)]))

(timed-test
 "Problem 24"
 '(2 7 8 3 9 1 5 4 6 0)
 (nth (permutations 0 1 2 3 4 5 6 7 8 9) 999999))
