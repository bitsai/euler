(ns p99
  (:use [euler :only (log max-by timed-test)])
  (:require [clojure.string :as str]))

;; x^y is proportional to y * log(x), which is much faster to compute
;; Only the latter is needed to find the correct line number

(defn line-value [i lines]
  (let [[base-str exp-str] (str/split (lines i) #",")
	base (Integer/parseInt base-str)
	exp (Integer/parseInt exp-str)]
    (* exp (log base))))

(timed-test
 709
 (let [lines (str/split-lines (slurp "../data/base_exp.txt"))]
   (inc (max-by #(line-value % lines) (range (count lines))))))
