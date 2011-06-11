(ns p69
  (:use [euler :only (primes product naturals timed-test)]))

;; n / phi(n) is max for n that is product of most consecutive primes
(defn product-k-consecutive-primes [k]
  (product (take k (primes))))

(timed-test
 510510
 (let [products (map product-k-consecutive-primes (naturals))]
   (last (take-while #(<= % 1000000) products))))
