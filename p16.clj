(ns p16
  (:use [utils :only (digits sum timed-test)])
  (:use [clojure.contrib.math :only (expt)]))

(timed-test
 "Problem 16"
 1366
 (sum (digits (expt 2 1000))))
