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

;; Numbers whose chain reach 89 are "sad" numbers
(defn sad? [n]
  (cond
   (= 1 n) false
   (= 89 n) true
   :else (recur (next-num n))))

;; All n < 10^7 chain to a number < 568
;; So create a sadness table for n < 568
;; Then we can determine sadness for all n's by looking up (next-num n)
(defn make-sad-table []
  (let [arr (boolean-array 568 false)]
    (doseq [sad-n (filter sad? (range 2 568))]
      (aset arr sad-n true))
    arr))

(timed-test
 8581146
 (let [^booleans table (make-sad-table)]
   (count-if #(aget table (next-num %)) (range 2 10000000))))
