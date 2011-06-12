(ns p35
  (:use [euler :only (digits prime? prime-sieve timed-test)]))

(defn rotate [s]
  (str (subs s 1) (subs s 0 1)))

(defn rotations [n]
  (let [n-str (str n)
        strs (take (count n-str) (iterate rotate n-str))]
    (map #(Integer/parseInt %) strs)))

(timed-test
 55
 (count (reduce (fn [circular-primes prime]
                  (let [rots (rotations prime)]
                    (if (and (not (circular-primes prime))
                             (every? odd? (digits prime))
                             (every? prime? rots))
                      (apply conj circular-primes rots)
                      circular-primes)))
                #{2}
                (prime-sieve 1000000))))
