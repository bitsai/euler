(ns p37
  (:use [euler :only (digits prime? prime-sieve sum-if timed-test)]))

(defn truncate-left [s]
  (subs s 1))

(defn truncate-right [s]
  (subs s 0 (dec (count s))))

(defn truncations [n]
  (let [n-str (str n)
        len (count n-str)
        strs (concat (take len (iterate truncate-left n-str))
                     (take len (iterate truncate-right n-str)))]
    (map #(Integer/parseInt %) strs)))

(timed-test
 748317
 (sum-if (fn [p] (and (> p 10)
                      (every? #(or (odd? %) (= 2 %)) (digits p))
                      (every? prime? (truncations p))))
         (prime-sieve 1000000)))
