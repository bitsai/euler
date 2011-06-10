(ns p20
  (:use [euler :only (factorial digits sum timed-test)]))

(timed-test
 648
 (sum (digits (factorial 100))))
