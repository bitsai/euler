(ns p28
  (:use [utils :only (timed-test)]))

(defn corner-nums [n]
  (for [i (range 4)]
    (- (* n n) (* i (dec n)))))

(defn sum-all-corner-nums [max-n]
  (let [ns (range 3 (inc max-n) 2)
	all-corner-nums (mapcat corner-nums ns)]
    (apply + 1 all-corner-nums)))

(timed-test
 "Problem 28"
 669171001
 (sum-all-corner-nums 1001))
