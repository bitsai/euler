(ns p67
  (:use [euler :only (max-of timed-test)])
  (:require [clojure.string :as str]))

(defn read-triangle []
  (for [line (str/split-lines (slurp "../data/triangle.txt"))]
    (for [token (str/split line #" ")]
      (Integer/parseInt token))))

(defn best-paths-row [row prev-best-paths-row]
  (for [i (range (count row))]
    (+ (nth row i) (max (nth prev-best-paths-row i 0)
			(nth prev-best-paths-row (dec i) 0)))))

(defn best-paths-triangle [[row & rows] triangle]
  (if-not row
    triangle
    (let [new-best-paths-row (best-paths-row row (peek triangle))]
      (recur rows (conj triangle new-best-paths-row)))))

(timed-test
 7273
 (max-of (peek (best-paths-triangle (read-triangle) []))))
