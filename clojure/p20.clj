(ns p20
  (:use [utils :only (factorial digits sum timed-test)]))

(timed-test
 "Problem 20"
 648
 (sum (digits (factorial 100))))