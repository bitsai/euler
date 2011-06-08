(ns p6
  (:use [euler :only (naturals sqr sum timed-test)]))

(timed-test
 "Problem 6"
 25164150
 (let [nums (take 100 (naturals))
       sum-of-squares (sum (map sqr nums))
       square-of-sum (sqr (sum nums))]
   (- square-of-sum sum-of-squares)))
