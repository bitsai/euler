(ns p14
  (:use [utils :only (timed-test)]))

(defn count-terms [n]
  (loop [n (long n)
	 count (long 1)]
    (if (= n (long 1))
      count
      (recur (if (bit-test n 0)
	       (unchecked-inc (unchecked-multiply n (long 3)))
	       (unchecked-divide n (long 2)))
	     (unchecked-inc count)))))

(timed-test
 "Problem 14"
 837799
 (let [make-pairs (juxt identity count-terms)
       f #(first (apply max-key second (map make-pairs %)))
       input [(range 1 250000)
	      (range 250000 500000)
	      (range 500000 750000)
	      (range 750000 1000000)]]
   (reduce max (pmap f input))))
