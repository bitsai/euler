(ns euler
  (:require [clojure.contrib.math :as math])
  (:require [clojure.contrib.seq :as seq]))

;; Testing macro
(defmacro timed-test [name answer expr]
  `(do
     (println ~name)
     (let [result# (time ~expr)]
       (println "[" (if (= result# ~answer) "OK" "FAIL") "]"))))

;; Basic functions
(defn abs [n]
  (math/abs n))

(defn sqr [n]
  (* n n))

(defn sqrt [n]
  (math/sqrt n))

(defn expt [base pow]
  (math/expt base pow))

(defn digits [n]
  (map #(Integer/parseInt (str %)) (str n)))

;; Sequence functions
(defn sum [coll]
  (reduce + coll))

(defn sum-if [pred coll]
  (sum (filter pred coll)))

(defn max-of [coll]
  (reduce max coll))

(defn max-key [k & xs]
  (first (reduce (fn [x y] (if (> (second x) (second y)) x y))
                 (map (juxt identity k) xs))))

(defn product [coll]
  (reduce * coll))

(defn find-first [pred coll]
  (seq/find-first pred coll))

(defn count-if [pred coll]
  (count (filter pred coll)))

;; Predicates
(defn divides? [a b]
  (zero? (rem a b)))

(defn prime? [n]
  (if (>= n 2)
    (let [root (sqrt n)
	  xs (cons 2 (range 3 (inc root) 2))]
      (not-any? #(divides? n %) xs))))

(defn palindrome? [n]
  (let [s (str n)]
    (= (seq s) (reverse s))))

;; Factorization
(defn factors [n]
  (let [root (sqrt n)
	pairs (for [i (range 1 root) :when (divides? n i)] [i (/ n i)])
	factors (reduce into [] pairs)]
    (if (divides? n root)
      (conj factors root)
      factors)))

(defn prime-factors [n]
  (filter prime? (factors n)))

;; GCD/LCM
(defn gcd [a b]
  (if (zero? b)
    a
    (recur b (rem a b))))

(defn lcm [a b]
  (/ (abs (* a b)) (gcd a b)))

;; Factorials
(defn factorial [n]
  (product (range 1 (inc n))))

(defn n-choose-k [n k]
  (/ (factorial n) (* (factorial k) (factorial (- n k)))))

;; Sequences
(defn fibs []
  (let [f (fn [[a b]] [b (+ a b)])]
    (map first (iterate f [0 1]))))

(defn naturals []
  (rest (range)))

(defn primes []
  (filter prime? (naturals)))

(defn prime-sieve [n]
  (if (> n 2)
    (loop [[p :as ps] (range 3 n 2)
           primes [2]]
      (cond
       (empty? ps) primes
       (>= (sqr p) n) (concat primes ps)
       :else (recur (remove #(divides? % p) ps)
                    (conj primes p))))))
