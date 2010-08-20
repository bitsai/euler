(ns p7
  (:use [utils :only (timed-test)])
  (:use [clojure.contrib.lazy-seqs :only (primes)]))

(timed-test
 "Problem 7"
 104743
 (nth primes 10000))
