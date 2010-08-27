(ns p26
  (:use [utils :only (has? timed-test)]))

(defn f [n d rs]
  (let [r (rem (* n 10) d)]
    (cond
     (zero? r) 0
     (has? rs r) (count (drop-while #(not= r %) rs))
     :else (recur r d (conj rs r)))))

(defn period [d] (f 1 d []))

(timed-test
 "Problem 26"
 983
 (apply max-key period (range 1 1000)))
