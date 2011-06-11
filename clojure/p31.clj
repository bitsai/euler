(ns p31
  (:use [euler :only (sum timed-test)]))

(def currencies [1 2 5 10 20 50 100 200])

(defn count-ways [amount i]
  (if (zero? i)
    1
    (let [currency (currencies i)
	  multiples (range 0 (inc amount) currency)
	  remainders (map #(- amount %) multiples)]
      (sum (map #(count-ways % (dec i)) remainders)))))

(timed-test
 73682
 (count-ways 200 (dec (count currencies))))
