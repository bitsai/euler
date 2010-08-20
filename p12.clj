(ns p12
  (:use [utils :only (factors triangle-nums timed-test)]))

(timed-test
 "Problem 12"
 76576500
 (let [lte-500-factors? #(<= (count (factors %)) 500)]
   (first (drop-while lte-500-factors? (triangle-nums)))))
