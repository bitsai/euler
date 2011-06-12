(ns p92
  (:use [euler :only (count-if timed-test)]))

(defn next-num [n]
  (loop [n (int n)
	 sum (int 0)]
    (let [digit (unchecked-remainder n (int 10))
	  new-sum (unchecked-add sum (unchecked-multiply digit digit))]
      (if (< n 10)
	new-sum
	(recur (unchecked-divide n (int 10))
               new-sum)))))

(defn reaches-89? [n]
  (cond
   (zero? n) false
   (= 1 n) false
   (= 89 n) true
   :else (recur (next-num n))))

;; For all n < 10^7, (next-num n) < 568
;; So create a table for n < 568
;; Then we can check all n's by simply looking up (next-num n)
(defn make-table []
  (let [arr (boolean-array 568 false)]
    (doseq [i (filter reaches-89? (range 568))]
      (aset arr i true))
    arr))

(timed-test
 8581146
 (let [^booleans table (make-table)]
   (count-if #(aget table (next-num %)) (range 10000000))))
