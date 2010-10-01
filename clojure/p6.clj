(ns p6
  (:use [utils :only (sq sum timed-test)]))

(timed-test
 "Problem 6"
 25164150
 (let [nums (range 1 (inc 100))
       sum-of-squares (sum (map sq nums))
       square-of-sum (sq (sum nums))]
   (- square-of-sum sum-of-squares)))
