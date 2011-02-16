(ns p42
  (:use [utils :only (digits timed-test)]))

(defn fraction-digits []
  (mapcat digits (range)))

(defn d [n]
  (nth (fraction-digits) n))

(timed-test
 "Problem 40"
 210
 (let [ns (take 7 (iterate #(* % 10) 1))]
   (reduce * (map d ns))))
