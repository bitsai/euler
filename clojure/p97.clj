(ns p97
  (:use [euler :only (expt-mod-n digits timed-test)]))

(timed-test
 '(8 7 3 9 9 9 2 5 7 7)
 (let [power-of-two (expt-mod-n 2 7830457 10000000000)
       prime (inc (* 28433 power-of-two))]
   (take-last 10 (digits prime))))
