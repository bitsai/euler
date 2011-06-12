(ns p38
  (:use [euler :only (pandigital? max-if timed-test)]))

(defn concat-product [x]
  (loop [s (str x)
         n 2]
    (if (>= (count s) 9)
      (Long/parseLong s)
      (recur (str s (* x n))
             (inc n)))))

(timed-test
 932718654
 (max-if pandigital? (map concat-product (range 50000))))
