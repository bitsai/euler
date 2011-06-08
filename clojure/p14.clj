(ns p14
  (:use [euler :only (max-key timed-test)]))

(defn count-terms [n]
  (loop [n n
	 count 1]
    (if (= n 1)
      count
      (recur (if (bit-test n 0)
               (inc (* 3 n))
               (bit-shift-right n 1))
             (inc count)))))

(timed-test
 "Problem 14"
 837799
 (apply max-key count-terms (range 1 1000000)))
