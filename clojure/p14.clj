(ns p14
  (:use [utils :only (timed-test)]))

;; Tell Nick about jvm server mode

(defn next-term [n]
  (if (even? n) (/ n 2)
      (inc (* n 3))))

(defn count-terms [n]
  (if (= n 1) 1
      (inc (count-terms (next-term n)))))

(def count-terms (memoize count-terms))

(timed-test
 "Problem 14"
 837799
 (apply max-key count-terms (range 1 1000000)))
