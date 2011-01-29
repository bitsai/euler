(ns p92
  (:use [utils :only (count-if timed-test)]))

(defn next-num [n]
  (loop [n (int n)
	 acc (int 0)]
    (let [digit (unchecked-remainder n (int 10))
	  new-acc (unchecked-add acc (unchecked-multiply digit digit))]
      (if (< n (int 10))
	new-acc
	(recur (unchecked-divide n (int 10)) new-acc)))))

(defn sad? [n]
  (cond
   (= 1 n) false
   (= 89 n) true
   :else (sad? (next-num n))))

(def sad-table (let [nums (range 1 (inc 567))
		     sadness (map sad? nums)]
		 (zipmap nums sadness)))

(defn sad? [n]
  (sad-table (next-num n)))

(timed-test
 "Problem 92"
 8581146
 (let [f #(count-if sad? %)
       input [(range 1 2500000)
	      (range 2500000 5000000)
	      (range 5000000 7500000)
	      (range 7500000 10000000)]]
   (reduce + (pmap f input))))
