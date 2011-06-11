(ns p30
  (:use [euler :only (digits expt sum sum-if timed-test)]))

(defn sum-of-5th-powers? [n]
  (let [fifth-powers (map #(expt % 5) (digits n))]
    (= n (sum fifth-powers))))

(timed-test
 443839
 (let [max-n (* 6 (expt 9 5))
       nums (range 2 (inc max-n))]
   (sum-if sum-of-5th-powers? nums)))
