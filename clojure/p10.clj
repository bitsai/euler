(ns p10
  (:use [utils :only (sum timed-test)])
  (:use [clojure.contrib.lazy-seqs :only (primes)]))

(timed-test
 "Problem 10"
 142913828922
 (let [lte-2-mil? #(<= % 2000000)]
   (sum (take-while lte-2-mil? primes))))
