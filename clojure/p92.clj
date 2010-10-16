(ns p92
  (:use [utils :only (sq timed-test)]))

(defn ^:static next-num [n]
  (loop [n n
	 acc 0]
    (let [digit (mod n 10)
	  new-acc (+ acc (sq digit))]
      (if (< n 10)
	new-acc
	(recur (quot n 10) new-acc)))))

(defn sad? [n]
  (cond
   (= 1 n) false
   (= 89 n) true
   :else (sad? (next-num n))))

(def sad-table (let [nums (range 1 (inc 567))
		     sadness (map sad? nums)]
		 (zipmap nums sadness)))

(timed-test
 "Problem 92"
 8581146
 (let [nums (range 1 10000000)
       sad-nums (filter #(sad-table (next-num %)) nums)]
   (count sad-nums)))
