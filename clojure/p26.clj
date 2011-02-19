(ns p26
  (:use [utils :only (timed-test)]))

(defn period
  ([d] (period 1 d []))
  ([n d remainders]
     (let [r (rem (* n 10) d)]
       (cond
	(zero? r) 0
	(some #{r} remainders) (count (drop-while #(not= r %) remainders))
	:else (period r d (conj remainders r))))))

(timed-test
 "Problem 26"
 983
 (let [pairs (map (juxt identity period) (range 1 1000))]
   (first (apply max-key second pairs))))
