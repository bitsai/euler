(ns p3
  (:use [utils :only (prime-factors timed-test)]))

(timed-test
 "Problem 3"
 6857
 (apply max (prime-factors 600851475143)))
