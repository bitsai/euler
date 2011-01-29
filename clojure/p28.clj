(ns p28
  (:use [utils :only (sum timed-test)])
  (:use [clojure.contrib.generic.math-functions :only (sqr)]))

(defn get-corner-nums [n]
  (for [i (range 4)]
    (- (sqr n) (* i (dec n)))))

(timed-test
 "Problem 28"
 669171001
 (let [dimensions (range 3 (inc 1001) 2)
       corner-nums (apply concat (map get-corner-nums dimensions))]
   (sum (cons 1 corner-nums))))
