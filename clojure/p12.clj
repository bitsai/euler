(ns p12
  (:use [utils :only (factors triangle-nums timed-test)]))

(defn count-triangle-num-factors [n]
  (let [D #(count (factors %))]
    (cond
     (even? n) (* (D (/ n 2)) (D (inc n)))
     :else (* (D n) (D (/ (inc n) 2))))))

(defn lte-500-factors? [[t n]]
  (<= (count-triangle-num-factors n) 500))

(timed-test
 "Problem 12"
 76576500
 (let [pairs (map vector (triangle-nums) (next (range)))]
   (ffirst (drop-while lte-500-factors? pairs))))
