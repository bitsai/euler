(ns p69
  (:use [utils :only (product timed-test)])
  (:use [clojure.contrib.lazy-seqs :only (primes)]))

(timed-test
 "Problem 69"
 510510
 (let [product-k-smallest-primes #(product (take % primes))
       products (map product-k-smallest-primes (next (range)))
       products-lte-1000000 (take-while #(<= % 1000000) products)]
   (last products-lte-1000000)))
