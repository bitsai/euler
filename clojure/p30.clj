(ns p30
  (:use [utils :only (digits expt sum timed-test)]))

(defn sum-of-5th-powers? [n]
  (let [fifth-powers (map #(expt % 5) (digits n))]
    (= n (sum fifth-powers))))

(timed-test
 "Problem 30"
 443839
 (let [max-n (* 6 (expt 9 5))
       nums (filter sum-of-5th-powers? (range 2 (inc max-n)))]
   (sum nums)))
