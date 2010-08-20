(ns p1
  (:use [utils :only (divides? sum timed-test)]))

(timed-test
 "Problem 1"
 233168
 (let [divides-3-or-5? #(or (divides? % 3) (divides? % 5))]
   (sum (filter divides-3-or-5? (range 1000)))))
