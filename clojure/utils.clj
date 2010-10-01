(ns utils
  (:use [clojure.contrib.generic.math-functions :only (sqrt)])
  (:require [clojure.string :as str]))

(defn ^:static even? [n] (false? (bit-test n 0)))

(defn ^:static expt [base pow] (reduce *' (repeat pow base)))

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

(defmacro timed-test [name answer code]
  `(do
     (println ~name)
     (let [my-answer# (time ~code)
	   status# (if (= my-answer# ~answer) "OK" "FAIL")]
       (println "[ " status# " ]"))))

(defn ^:static re-matches? [re s] (.matches (re-matcher re s)))

(defn ^:static divides? [n a] (zero? (rem n a)))

(defn ^:static sum [coll] (reduce +' coll))

(defn ^:static product [coll] (reduce *' coll))

(defn ^:static factors [n]
  (let [one-to-sqrt (range 1 (inc (sqrt n)))
	factor-pairs (for [i one-to-sqrt :when (divides? n i)]
		       [i (/ n i)])]
    (set (apply concat factor-pairs))))

(defn ^:static proper-divisors [n] (disj (factors n) n))

(defn ^:static split-commas [s] (str/split s #","))

(defn ^:static strip-quotes [s] (str/replace s "\"" ""))

(defn ^:static prime? [n]
  (cond
   (not (pos? n)) false
   (= 1 n) false
   (= 2 n) true
   :else (let [two-to-sqrt (range 2 (inc (sqrt n)))]
	   (not-any? #(divides? n %) two-to-sqrt))))

(defn ^:static prime-factors [n] (filter prime? (factors n)))

(defn ^:static palindrome? [n]
  (let [s (seq (str n))]
    (= s (reverse s))))

(defn ^:static sq [n] (* n n))

(defn ^:static digits [n]
  (loop [n n
	 acc '()]
    (let [new-acc (conj acc (mod n 10))]
      (if (< n 10)
	new-acc
	(recur (quot n 10) new-acc)))))

(defn ^:static pythagorean? [[a b c]] (= (+ (sq a) (sq b)) (sq c)))

(defn ^:static m-get [m [row col]] (nth (nth m row nil) col nil))

(defn ^:static factorial [n] (product (range 1 (inc n))))

(defn ^:static falling-factorial [n k] (product (take k (iterate dec n))))

(defn ^:static n-choose-k [n k] (/ (falling-factorial n k) (factorial k)))

(defn ^:static whole-nums [] (iterate inc 1))

(defn ^:static natural-nums [] (iterate inc 0))

(defn ^:static triangle-nums [] (map #(n-choose-k (inc %) 2) (whole-nums)))

(defn ^:static has? [coll x] (some #{x} coll))

(defn ^:static expt-mod-n
  ([base pow n] (expt-mod-n base pow n 1))
  ([base pow n acc]
     (let [new-acc (rem (* base acc) n)]
       (cond
	(= 0 pow) 1
	(= 1 pow) new-acc
	:else (recur base (dec pow) n new-acc)))))

(defn ^:static quadratic [n a b] (+ (sq n) (* a n) b))

(defn ^:static map-vals [f m] (zipmap (keys m) (map f (vals m))))
