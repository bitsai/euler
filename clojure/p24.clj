(ns p24
  (:use [utils :only (timed-test)])
  (:use [clojure.contrib.combinatorics :only (permutations)]))

(timed-test
 "Problem 24"
 "2783915460"
 (apply str (nth (permutations "0123456789") 999999)))
