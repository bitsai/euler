(ns p22
  (:use [utils :only (word-score strip-quotes split-commas sum timed-test)])
  (:use [clojure.java.io :only (reader)]))

(defn name-score [idx name]
  (* (inc idx) (word-score name)))

(timed-test
 "Problem 22"
 871198282
 (with-open [rdr (reader "../data/names.txt")]
   (let [lines (line-seq rdr)
	 names (split-commas (strip-quotes (first lines)))
	 scores (map-indexed name-score (sort names))]
     (sum scores))))
