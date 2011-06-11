(ns p26
  (:use [euler :only (max-key timed-test)]))

;; Get period of the repeating decimal 1/d by repeated long division
;; Track remainders we see along the way
;; When a remainder is seen for the second time, we can compute the period
(defn period
  ([d] (period 1 d []))
  ([n d remainders]
     (let [r (rem (* n 10) d)]
       (cond
	(zero? r) 0
	(some #{r} remainders) (count (drop-while #(not= r %) remainders))
	:else (recur r d (conj remainders r))))))

(timed-test
 983
 (apply max-key period (range 1 1000)))
