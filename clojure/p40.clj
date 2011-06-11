(ns p40
  (:use [euler :only (digits expt product timed-test)]))

(defn fraction-digits []
  (rest (mapcat digits (range))))

(defn d [n]
  (nth (fraction-digits) (dec n)))

(timed-test
 210
 (product (for [n (map #(expt 10 %) (range 7))]
            (d n))))
