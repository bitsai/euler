(ns p12
  (:use [clojure.contrib.seq :only (find-first)])
  (:use [utils :only (factors timed-test)]))

(defn nth-triangle-num [n]
  (* n (/ (inc n) 2)))

;; From Project Euler solution pdf
(defn count-nth-triangle-num-factors [n]
  (let [D (fn [x] (count (factors x)))]
    (if (even? n)
      (* (D (/ n 2)) (D (inc n)))
      (* (D n) (D (/ (inc n) 2))))))

(timed-test
 "Problem 12"
 76576500
 (let [n (find-first #(> (count-nth-triangle-num-factors %) 500) (range))]
   (nth-triangle-num n)))
