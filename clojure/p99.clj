(ns p99
  (:use [utils :only (split-commas parse-int timed-test)])
  (:use [clojure.java.io :only (reader)])
  (:use [clojure.contrib.generic.math-functions :only (log)]))

(defn process-line [idx line]
  (let [[base-str exp-str] (split-commas line)
	base (parse-int base-str)
	exp (parse-int exp-str)
	log-value (* exp (log base))]
    [log-value (inc idx)]))

(timed-test
 "Problem 99"
 709
 (with-open [rdr (reader "../data/base_exp.txt")]
   (let [lines (line-seq rdr)
	 pairs (map-indexed process-line lines)]
     (second (apply max-key first pairs)))))
