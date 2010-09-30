(ns p14
  (:use [utils :only (even? max-key timed-test)]))

;; Tell Nick about jvm server mode

(defn ^:static next-term ^long [^long n]
  (if (even? n)
    (bit-shift-right n 1)
    (inc (* n 3))))

(defn ^:static count-terms ^long [^long n]
  (if (= 1 n)
    1
    (inc (count-terms (next-term n)))))

(timed-test
 "Problem 14"
 837799
 (apply max-key count-terms (range 1 1000000)))
