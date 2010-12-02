(ns p42
  (:use [utils :only (digits timed-test)]))

(defn fraction-digits []
  (apply concat (map digits (range))))

(defn d [n]
  (nth (fraction-digits) n))

(timed-test
 "Problem 40"
 210
 (* (d 1)
    (d 10)
    (d 100)
    (d 1000)
    (d 10000)
    (d 100000)
    (d 1000000)))
