(ns p67
  (:require [clojure.string :as str])
  (:use [utils :only (timed-test)]))

(defn read-triangle []
  (let [lines (str/split-lines (slurp "../data/triangle.txt"))
	to-ints (fn [s] (map #(Integer/parseInt %) (str/split s #" ")))]
    (map to-ints lines)))

(defn get-totals [input last-totals]
  (for [i (range (count input))]
    (+ (nth input i) (max (nth last-totals i 0)
			  (nth last-totals (dec i) 0)))))

(defn max-total [[input & next-inputs] [last-totals & _ :as all-totals]]
  (if-not input
    (apply max last-totals)
    (let [new-totals (get-totals input last-totals)]
      (recur next-inputs (cons new-totals all-totals)))))

(timed-test
 "Problem 67"
 7273
 (max-total (read-triangle) '()))
