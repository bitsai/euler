(ns p32
  (:use [euler :only (pandigital? product sum timed-test)]))

(defn make-identity [[a b]]
  (str a b (* a b)))

(timed-test
 45228
 (let [one-by-fours (for [a (range 1 10)
                          b (range 1000 10000)]
                      [a b])
       two-by-threes (for [a (range 10 100)
                           b (range 100 1000)]
                       [a b])
       pairs (concat one-by-fours two-by-threes)
       pandigitals (filter #(pandigital? (make-identity %)) pairs)]
   (sum (distinct (map product pandigitals)))))
