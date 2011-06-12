(ns p4
  (:use [euler :only (palindrome? max-if timed-test)]))

(timed-test
 906609
 (let [products (for [x (range 100 1000)
                      y (range x 1000)]
                  (* x y))]
   (max-if palindrome? products)))
