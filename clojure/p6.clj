(ns p6
  (:use [euler :only (natural-nums sum timed-test)])
  (:use [clojure.contrib.generic.math-functions :only (sqr)]))

(timed-test
 "Problem 6"
 25164150
 (let [nums (take 100 (natural-nums))
       sum-of-squares (sum (map sqr nums))
       square-of-sum (sqr (sum nums))]
   (- square-of-sum sum-of-squares)))
