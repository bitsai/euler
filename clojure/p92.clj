(ns p92
  (:use [utils :only (digits sq sum timed-test)]))

(defn ^:static sorted-digits [n]
  (drop-while zero? (sort (digits n))))

(defn ^:static next-digits [digits]
  (let [next-n (sum (map sq digits))]
    (sorted-digits next-n)))

(defn ^:static sad? [digits]
  (case digits
	[1] false
	[8 9] true
	(sad? (next-digits digits))))

(def sad? (memoize sad?))

(timed-test
 "Problem 92"
 8581146
 (let [numbers (range 1 10000000)
       sad-numbers (filter #(sad? (sorted-digits %)) numbers)]
   (count sad-numbers)))
