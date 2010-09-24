(ns p14
  (:use [utils :only (even? timed-test)]))

;; Tell Nick about jvm server mode

(defn next-term [n]
  (if (even? n) (/ n 2)
      (inc (* n 3))))

(defn count-terms [n]
  (if (= 1 n) 1
      (inc (count-terms (next-term n)))))

(timed-test
 "Problem 14"
 837799
 (let [pair #(list % (count-terms %))
       pairs (map pair (range 1 1000000))]
   (first (apply max-key second pairs))))
