(ns p92
  (:use [utils :only (count-if timed-test)]))

(defn ^:static next-num [n]
  (loop [n (int n)
	 acc (int 0)]
    (let [digit (unchecked-remainder-int n (int 10))
	  square (unchecked-multiply-int digit digit)
	  new-acc (unchecked-add-int acc square)]
      (if (< n (int 10))
	new-acc
	(recur (unchecked-divide-int n 10) new-acc)))))

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
 (count-if sad? (range 1 10000000)))
