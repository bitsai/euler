(ns p31
  (:use [utils :only (sum timed-test)]))

(def currencies [1 2 5 10 20 50 100 200])

(defn count-ways [amount currency-type]
  (if (zero? currency-type)
    1
    (let [currency (currencies currency-type)
	  multiples (take-while #(<= % amount) (iterate #(+ % currency) 0))
	  remainders (map #(- amount %) multiples)]
      (sum (map #(count-ways % (dec currency-type)) remainders)))))

(timed-test
 "Problem 31"
 73682
 (count-ways 200 7))
