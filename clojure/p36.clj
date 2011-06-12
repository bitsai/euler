(ns p36
  (:use [euler :only (palindrome? sum-if timed-test)]))

(timed-test
 872187
 (sum-if #(and (palindrome? %)
               (palindrome? (Integer/toBinaryString %)))
         (range 1000000)))
