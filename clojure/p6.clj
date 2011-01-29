(ns p6
  (:use [utils :only (sum timed-test)])
  (:use [clojure.contrib.generic.math-functions :only (sqr)]))

(timed-test
 "Problem 6"
 25164150
 (let [nums (range 1 (inc 100))
       sum-of-squares (sum (map sqr nums))
       square-of-sum (sqr (sum nums))]
   (- square-of-sum sum-of-squares)))
