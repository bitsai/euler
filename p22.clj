(ns p22
  (:use [utils :only (sum timed-test)])
  (:use [clojure.java.io :only (reader)])
  (:require [clojure.string :as str]))

(def lines (with-open [rdr (reader "names.txt")] (line-seq rdr)))

(def names (let [strip-quotes #(str/replace % "\"" "")
		 split-commas #(str/split % #",")]
	     (split-commas (strip-quotes (first lines)))))

(defn name-score [idx name]
  (let [char-score #(- (int %) 64)
	word-score #(sum (map char-score %))]
    (* (inc idx) (word-score name))))

(timed-test
 "Problem 22"
 871198282
 (sum (map-indexed name-score (sort names))))
