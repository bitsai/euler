(ns utils
  (:use [clojure.contrib.generic.math-functions :only (round sqrt)])
  (:require [clojure.string :as str]))

(defn expt [base pow]
  (reduce * (repeat pow base)))

(defn fibs []
  (let [f (fn [[a b]] [b (+ a b)])]
    (map first (iterate f [0 1]))))

(defn max-key
  ([k x] x)
  ([k x & more]
     (loop [x x
            kx (k x)
            s more]
       (if-not s
	 x
	 (let [y (first s)
	       ky (k y)]
	   (if (> kx ky)
	     (recur x kx (next s))
	     (recur y ky (next s))))))))

(defn min-key
  ([k x] x)
  ([k x & more]
     (loop [x x
            kx (k x)
            s more]
       (if-not s
	 x
	 (let [y (first s)
	       ky (k y)]
	   (if (< kx ky)
	     (recur x kx (next s))
	     (recur y ky (next s))))))))

(defmacro timed-test [name answer code]
  `(do
     (println ~name)
     (let [my-answer# (time ~code)
	   status# (if (= my-answer# ~answer) "OK" "FAIL")]
       (println "[ " status# " ]"))))

(defn divides? [n a]
  (zero? (rem n a)))

(defn sum [coll]
  (reduce + coll))

(defn product [coll]
  (reduce * coll))

(defn count-if [pred coll]
  (count (filter pred coll)))

(defn sum-if [pred coll]
  (sum (filter pred coll)))

(defn factors [n]
  (let [root (sqrt n)
	int-root (round root)
	pairs (for [i (range 1 root) :when (divides? n i)] [i (/ n i)])
	factors (apply concat pairs)]
    (if (= root int-root)
      (conj factors int-root)
      factors)))

(defn proper-divisors [n]
  (remove #{n} (factors n)))

(defn split-commas [s]
  (str/split s #","))

(defn strip-quotes [s]
  (str/replace s "\"" ""))

(defn prime? [n]
  (if (< n 2)
    false
    (let [root (sqrt n)
	  two-to-sqrt (conj (range 2 root) root)]
      (not-any? #(divides? n %) two-to-sqrt))))

(defn prime-factors [n]
  (filter prime? (factors n)))

(defn palindrome? [s]
  (let [len (count s)
        mid (quot len 2)]
    (loop [i 0
           j (dec len)]
      (cond
       (= i mid) true
       (= (nth s i) (nth s j)) (recur (inc i) (dec j))
       :else false))))

(defn sq [n]
  (* n n))

(defn digits [n]
  (loop [n n
	 acc '()]
    (let [digit (rem n 10)
	  new-acc (conj acc digit)]
      (if (< n 10)
	new-acc
	(recur (quot n 10) new-acc)))))

(defn pythagorean? [[a b c]]
  (= (+ (sq a) (sq b)) (sq c)))

(defn m-get [m [row col]]
  (nth (nth m row nil) col nil))

(defn factorial [n]
  (product (range 1 (inc n))))

(defn falling-factorial [n k]
  (product (take k (iterate dec n))))

(defn n-choose-k [n k]
  (/ (falling-factorial n k) (factorial k)))

(defn whole-nums []
  (iterate inc 1))

(defn natural-nums []
  (iterate inc 0))

(defn triangle-nums []
  (map #(n-choose-k (inc %) 2) (whole-nums)))

(defn has? [coll x]
  (some #{x} coll))

(defn expt-mod-n
  ([base pow n] (expt-mod-n base pow n 1))
  ([base pow n acc]
     (let [new-acc (rem (* base acc) n)]
       (cond
	(= 0 pow) 1
	(= 1 pow) new-acc
	:else (recur base (dec pow) n new-acc)))))

(defn quadratic [n a b]
  (+ (sq n) (* a n) b))

(defn map-vals [f m]
  (zipmap (keys m) (map f (vals m))))

(defn word-score [word]
  (let [char-score #(- (int %) 64)]
    (sum (map char-score word))))

(defn s-gonal? [x s]
  (let [a (* (- (* 8 s) 16) x)
	b (sq (- s 4))
	numerator (- (+ (sqrt (+ a b)) s) 4)
	denominator (- (* 2 s) 4)
	n (/ numerator denominator)]
    (= n (round n))))
