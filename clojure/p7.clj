(ns p7
  (:use [euler :only (primes timed-test)]))

(timed-test
 "Problem 7"
 104743
 (nth (primes) 10000))
