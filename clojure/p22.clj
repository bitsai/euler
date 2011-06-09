(ns p22
  (:use [euler :only (str-score split-on-commas strip-quotes sum timed-test)])
  (:require [clojure.string :as str]))

(defn name-score [idx name]
  (* (inc idx) (str-score name)))

(timed-test
 "Problem 22"
 871198282
 (let [s (str/trim (slurp "../data/names.txt"))
       names (map strip-quotes (split-on-commas s))
       scores (map-indexed name-score (sort names))]
   (sum scores)))
