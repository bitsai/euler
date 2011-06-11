(ns p33
  (:use [euler :only (digits product gcd timed-test)]))

(defn curious-fraction? [[n d]]
  (let [[n1 n2] (digits n)
        [d1 d2] (digits d)]
    (and (not= n d)
         (pos? d2)
         (= n2 d1)
         (= (/ n d) (/ n1 d2)))))

(timed-test
 100
 (let [pairs (for [n (range 10 99)
                   d (range 11 100)]
               [n d])
       curious-fractions (filter curious-fraction? pairs)
       numerator (product (map first curious-fractions))
       denominator (product (map second curious-fractions))]
   (/ denominator (gcd numerator denominator))))
