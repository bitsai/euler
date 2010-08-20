(ns p6
  (:use [utils :only (square sum timed-test)]))

(timed-test
 "Problem 6"
 25164150
 (let [nums (range 1 (inc 100))
       sum-of-squares (sum (map square nums))
       square-of-sum (square (sum nums))]
   (- square-of-sum sum-of-squares)))
