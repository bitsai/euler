(ns p10
  (:use [euler :only (prime-sieve sum timed-test)]))

(timed-test
 142913828922
 (sum (prime-sieve 2000000)))
