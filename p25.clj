(ns p25
  (:use [utils :only (digits timed-test)])
  (:use [clojure.contrib.lazy-seqs :only (fibs)]))

(timed-test
 "Problem 25"
 4782
 (let [lt-1000-digits? #(< (count (digits %)) 1000)]
   (count (take-while lt-1000-digits? (fibs)))))
