(ns p2
  (:use [utils :only (sum timed-test)])
  (:use [clojure.contrib.lazy-seqs :only (fibs)]))

(timed-test
 "Problem 2"
 4613732
 (let [lte-4-mil? #(<= % 4000000)]
   (sum (filter even? (take-while lte-4-mil? (fibs))))))
