(ns p1
  (:use [euler :only (divides? sum-if timed-test)]))

(timed-test
 "Problem 1"
 233168
 (sum-if #(or (divides? % 3) (divides? % 5)) (range 1000)))
