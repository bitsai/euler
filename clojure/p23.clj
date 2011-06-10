(ns p23
  (:use [euler :only (proper-divisors sum timed-test)]))

(defn abundant? [n]
  (> (sum (proper-divisors n)) n))

(timed-test
 "Problem 23"
 4179871
 (let [n 20162
       nums (range 1 n)
       abundants (vec (filter abundant? nums))
       add-abundants (fn [i j] (+ (abundants i) (abundants j)))
       sums-of-abundants (boolean-array n false)]
   (doseq [i (range (count abundants))
           j (range i (count abundants))
           :while (< (add-abundants i j) n)]
     (aset sums-of-abundants (add-abundants i j) true))
   (sum (remove #(aget sums-of-abundants %) nums))))
