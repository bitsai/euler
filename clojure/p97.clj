(ns p97
  (:use [utils :only (expt-mod-n digits timed-test)])
  (:use [clojure.contrib.math :only (expt)]))

(timed-test
 "Problem 97"
 "8739992577"
 (let [power-of-two (expt-mod-n 2 7830457 10000000000)
       big-num (inc (* 28433 power-of-two))]
   (apply str (take-last 10 (digits big-num)))))
