(ns p28
  (:use [utils :only (square sum timed-test)]))

(defn get-corner-nums [n]
  (for [i (range 4)]
    (- (square n) (* i (dec n)))))

(timed-test
 "Problem 28"
 669171001
 (let [dimensions (range 3 (inc 1001) 2)
       corner-nums (apply concat (map get-corner-nums dimensions))]
   (sum (cons 1 corner-nums))))
