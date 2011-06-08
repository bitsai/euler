(ns p3
  (:use [euler :only (prime-factors max-of timed-test)]))

(timed-test
 "Problem 3"
 6857
 (max-of (prime-factors 600851475143)))
