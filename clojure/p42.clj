(ns p42
  (:use [utils :only (strip-quotes split-commas word-score s-gonal? count-if timed-test)])
  (:use [clojure.java.io :only (reader)]))

(timed-test
 "Problem 42"
 162
 (with-open [rdr (reader "../data/words.txt")]
   (let [lines (line-seq rdr)
	 words (split-commas (strip-quotes (first lines)))
	 scores (map word-score words)]
     (count-if #(s-gonal? % 3) scores))))
