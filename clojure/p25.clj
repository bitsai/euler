(ns p25
  (:use [euler :only (digits fibs timed-test)]))

(timed-test
 "Problem 25"
 4782
 (let [lt-1000-digits? (fn [n] (< (count (digits n)) 1000))]
   (count (take-while lt-1000-digits? (fibs)))))
