(ns p2
  (:use [euler :only (fibs sum-if timed-test)]))

(timed-test
 4613732
 (sum-if even? (take-while #(<= % 4000000) (fibs))))
