(ns p12
  (:use [utils :only (factors triangle-nums timed-test)]))

(defn count-triangle-factors [n]
  (let [D #(count (factors %))]
    (cond
     (even? n) (* (D (/ n 2)) (D (inc n)))
     :else (* (D n) (D (/ (inc n) 2))))))

(timed-test
 "Problem 12"
 76576500
 (let [n (first (filter #(> (count-triangle-factors %) 500) (range)))]
   (nth (triangle-nums) (dec n))))
