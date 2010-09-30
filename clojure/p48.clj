(ns p48
  (:use [utils :only (expt sum digits timed-test)]))

(timed-test
 "Problem 48"
 "9110846700"
 (let [nums (for [x (range 1 1000)] (expt x x))
       last-10-digits (take-last 10 (digits (sum nums)))]
   (apply str last-10-digits)))
