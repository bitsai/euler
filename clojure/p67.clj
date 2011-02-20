(ns p67
  (:require [clojure.string :as str])
  (:use [utils :only (timed-test)]))

(defn read-triangle []
  (let [lines (str/split-lines (slurp "../data/triangle.txt"))
	to-ints (fn [s] (map #(Integer/parseInt %) (str/split s #" ")))]
    (map to-ints lines)))

(defn row-totals [row last-totals]
  (for [i (range (count row))]
    (+ (nth row i) (max (nth last-totals i 0)
			(nth last-totals (dec i) 0)))))

(defn triangle-totals [[row & next-rows] totals]
  (if-not row
    totals
    (let [new-totals (row-totals row (last totals))]
      (recur next-rows (conj totals new-totals)))))

(timed-test
 "Problem 67"
 7273
 (apply max (last (triangle-totals (read-triangle) []))))
