(ns utils
  (:use [clojure.contrib.generic.math-functions :only (round sqrt)])
  (:require [clojure.string :as str]))

(defn ^:static even? [n]
  (false? (bit-test n 0)))

(defn ^:static expt [base pow]
  (reduce *' (repeat pow base)))

(defn ^:static fibs []
  (let [f (fn [[a b]] [b (+' a b)])]
    (map first (iterate f [0 1]))))

(defn ^:static max-key
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

(defn ^:static min-key
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

(defn ^:static divides? [n a]
  (zero? (rem n a)))

(defn ^:static sum [coll]
  (reduce +' coll))

(defn ^:static product [coll]
  (reduce *' coll))

(defn ^:static count-if [pred coll]
  (count (filter pred coll)))

(defn ^:static sum-if [pred coll]
  (sum (filter pred coll)))

(defn ^:static factors [n]
  (let [root (sqrt n)
	int-root (round root)
	pairs (for [i (range 1 root) :when (divides? n i)] [i (/ n i)])
	factors (apply concat pairs)]
    (if (== root int-root)
      (conj factors int-root)
      factors)))

(defn ^:static proper-divisors [n]
  (remove #{n} (factors n)))

(defn ^:static split-commas [s]
  (str/split s #","))

(defn ^:static strip-quotes [s]
  (str/replace s "\"" ""))

(defn ^:static prime? [n]
  (if (< n 2)
    false
    (let [root (sqrt n)
	  two-to-sqrt (conj (range 2 root) root)]
      (not-any? #(divides? n %) two-to-sqrt))))

(defn ^:static prime-factors [n]
  (filter prime? (factors n)))

(defn ^:static palindrome? [s]
  (let [len (count s)
        mid (quot len 2)]
    (loop [i 0
           j (dec len)]
      (cond
       (= i mid) true
       (= (nth s i) (nth s j)) (recur (inc i) (dec j))
       :else false))))

(defn ^:static sq [n]
  (* n n))

(defn ^:static digits [n]
  (loop [n n
	 acc '()]
    (let [new-acc (conj acc (mod n 10))]
      (if (< n 10)
	new-acc
	(recur (quot n 10) new-acc)))))

(defn ^:static pythagorean? [[a b c]]
  (= (+ (sq a) (sq b)) (sq c)))

(defn ^:static m-get [m [row col]]
  (nth (nth m row nil) col nil))

(defn ^:static factorial [n]
  (product (range 1 (inc n))))

(defn ^:static falling-factorial [n k]
  (product (take k (iterate dec n))))

(defn ^:static n-choose-k [n k]
  (/ (falling-factorial n k) (factorial k)))

(defn ^:static whole-nums []
  (iterate inc 1))

(defn ^:static natural-nums []
  (iterate inc 0))

(defn ^:static triangle-nums []
  (map #(n-choose-k (inc %) 2) (whole-nums)))

(defn ^:static has? [coll x]
  (some #{x} coll))

(defn ^:static expt-mod-n
  ([base pow n] (expt-mod-n base pow n 1))
  ([base pow n acc]
     (let [new-acc (rem (* base acc) n)]
       (cond
	(= 0 pow) 1
	(= 1 pow) new-acc
	:else (recur base (dec pow) n new-acc)))))

(defn ^:static quadratic [n a b]
  (+ (sq n) (* a n) b))

(defn ^:static map-vals [f m]
  (zipmap (keys m) (map f (vals m))))
