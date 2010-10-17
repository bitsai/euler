(ns p14
  (:use [utils :only (max-key timed-test)]))

;; Tell Nick about jvm server mode

(defn ^:static next-term ^long [^long n]
  (if (zero? (unchecked-remainder-long n 2))
    (unchecked-divide-long n 2)
    (unchecked-inc-long (unchecked-multiply-long n 3))))

(defn ^:static count-terms ^long [^long n]
  (if (= n 1)
    1
    (unchecked-inc-long (count-terms (next-term n)))))

(timed-test
 "Problem 14"
 837799
 (apply max-key count-terms (range 1 1000000)))
