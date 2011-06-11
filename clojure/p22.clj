(ns p22
  (:use [euler :only (word-value read-quoted-csv sum timed-test)])
  (:require [clojure.string :as str]))

(defn name-score [idx name]
  (* (inc idx) (word-value name)))

(timed-test
 871198282
 (let [names (read-quoted-csv "../data/names.txt")]
   (sum (map-indexed name-score (sort names)))))
