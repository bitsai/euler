(ns p14
  (:use [utils :only (timed-test)]))

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
 (let [pairs (map #(list % (count-terms %)) (range 1 1000000))]
   (first (apply max-key second pairs))))
