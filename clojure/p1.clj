(ns p1
  (:use [utils :only (multiple? sum-if timed-test)]))

(timed-test
 "Problem 1"
 233168
 (let [multiple-of-3-or-5? #(or (multiple? % 3) (multiple? % 5))]
   (sum-if multiple-of-3-or-5? (range 1000))))
