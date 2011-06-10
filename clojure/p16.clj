(ns p16
  (:use [euler :only (expt digits sum timed-test)]))

(timed-test
 1366
 (sum (digits (expt 2 1000))))
