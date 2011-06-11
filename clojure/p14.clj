(ns p14
  (:use [euler :only (max-by timed-test)]))

(def N 1000000)

(def table (int-array N 0))

(defn memoized? [n]
  (and (< n N) (pos? (aget ^ints table n))))

(defn collatz [n]
  (if (bit-test n 0)
    (inc (* 3 n))
    (/ n 2)))

(defn count-terms [n]
  (cond
   (= n 1) 1
   (memoized? n) (aget ^ints table n)
   :else (let [cnt (inc (count-terms (collatz n)))]
           (if (< n N)
             (aset ^ints table n (int cnt)))
           cnt)))

(timed-test
 837799
 (max-by count-terms (range 1 N)))
