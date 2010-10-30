(ns p2
  (:use [utils :only (fibs sum-if timed-test)]))

(timed-test
 "Problem 2"
 4613732
 (let [lte-4-mil? #(<= % 4000000)]
   (sum-if even? (take-while lte-4-mil? (fibs)))))
