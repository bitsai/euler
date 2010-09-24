(ns p16
  (:use [utils :only (expt digits sum timed-test)]))

(timed-test
 "Problem 16"
 1366
 (sum (digits (expt 2 1000))))
