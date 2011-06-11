(ns p48
  (:use [euler :only (expt sum digits timed-test)]))

(timed-test
 '(9 1 1 0 8 4 6 7 0 0)
 (let [expts (for [x (range 1 (inc 1000))] (expt x x))]
   (take-last 10 (digits (sum expts)))))
