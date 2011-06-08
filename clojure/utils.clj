(ns utils
  (:use [clojure.contrib.generic.math-functions :only (sqr sqrt)])
  (:require [clojure.string :as str]))

(defmacro timed-test [name answer code]
  `(do
     (println ~name)
     (let [result# (time ~code)
	   status# (if (= result# ~answer) "OK" "FAIL")]
       (println "[ " status# " ]"))))

(defn multiple? [m n]
  (zero? (rem m n)))

(defn sum [coll]
  (reduce + coll))

(defn product [coll]
  (reduce * coll))

(defn expt [base pow]
  (product (repeat pow base)))

(defn fibs []
  (let [f (fn [[a b]] [b (+ a b)])]
    (map first (iterate f [0 1]))))

(defn count-if [pred coll]
  (count (filter pred coll)))

(defn sum-if [pred coll]
  (sum (filter pred coll)))

(defn factors [n]
  (let [root (sqrt n)
	int-root (int root)
	pairs (for [i (range 1 root) :when (multiple? n i)] [i (/ n i)])
	factors (reduce into [] pairs)]
    (if (= root int-root)
      (cons int-root factors)
      factors)))

(defn proper-divisors [n]
  (remove #{n} (factors n)))

(defn split-commas [s]
  (str/split s #","))

(defn strip-quotes [s]
  (str/replace s "\"" ""))

(defn prime? [n]
  (if (>= n 2)
    (let [root (sqrt n)
	  xs (cons 2 (range 3 (inc root) 2))]
      (not-any? #(multiple? n %) xs))))

(defn prime-factors [n]
  (filter prime? (factors n)))

(defn prime-sieve [n]
  (if (>= n 2)
    (loop [[x & _ :as xs] (cons 2 (range 3 (inc n) 2))
	   primes []]
      (cond
       (> (sqr x) n) (into primes xs)
       :else (recur (remove #(multiple? % x) xs)
		    (conj primes x))))))

(defn palindrome? [s]
  (= (seq s) (reverse s)))

(defn digits [n]
  (loop [n n
	 digits-so-far '()]
    (if (< n 10)
      (cons n digits-so-far)
      (recur (quot n 10)
	     (cons (rem n 10) digits-so-far)))))

(defn pythagorean? [[a b c]]
  (= (+ (sqr a) (sqr b)) (sqr c)))

(defn factorial [n]
  (product (range 1 (inc n))))

(defn falling-factorial [n k]
  (product (take k (iterate dec n))))

(defn n-choose-k [n k]
  (/ (falling-factorial n k) (factorial k)))

(defn triangle-nums []
  (reductions + (rest (range))))

(defn expt-mod-n
  ([base pow n] (expt-mod-n base pow n 1))
  ([base pow n acc]
     (let [new-acc (rem (* base acc) n)]
       (cond
	(= 0 pow) 1
	(= 1 pow) new-acc
	:else (recur base (dec pow) n new-acc)))))

(defn quadratic [n a b]
  (+ (sqr n) (* a n) b))

(defn map-vals [f m]
  (zipmap (keys m) (map f (vals m))))

(defn word-score [word]
  (let [char-score #(- (int %) 64)]
    (sum (map char-score word))))

(defn s-gonal? [x s]
  (let [a (* (- (* 8 s) 16) x)
	b (sqr (- s 4))
	numerator (- (+ (sqrt (+ a b)) s) 4)
	denominator (- (* 2 s) 4)
	n (/ numerator denominator)]
    (= n (int n))))
