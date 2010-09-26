(ns utils
  (:use [clojure.contrib.generic.math-functions :only (sqrt)])
  (:require [clojure.string :as str]))

(defn even? [n] (false? (bit-test n 0)))

(defn expt [base pow] (reduce *' (repeat pow base)))

(defn fibs [] (map first (iterate (fn [[a b]] [b (+' a b)]) [0 1])))

(defn max-key
  ([k x] x)
  ([k x y] (if (> (k x) (k y)) x y))
  ([k x y & more]
     (second (reduce (fn [x y] (if (> (first x) (first y)) x y))
                     (map (juxt k identity) (cons x (cons y more)))))))

(defmacro timed-test [name answer code]
  `(do
     (println ~name)
     (let [my-answer# (time ~code)
	   status# (if (= my-answer# ~answer) "OK" "FAIL")]
       (println "[ " status# " ]"))))

(defn re-matches? [re s] (.matches (re-matcher re s)))

(defn divides? [dividend divisor] (zero? (rem dividend divisor)))

(defn sum [coll] (reduce +' coll))

(defn product [coll] (reduce *' coll))

(defn factors [n]
  (let [one-to-sqrt (range 1 (inc (sqrt n)))
	factors (for [i one-to-sqrt :when (divides? n i)]
		  [i (/ n i)])]
    (set (apply concat factors))))

(defn proper-divisors [n] (disj (factors n) n))

(defn split-commas [s] (str/split s #","))

(defn strip-quotes [s] (str/replace s "\"" ""))

(defn prime? [n] (= 2 (count (factors n))))

(defn prime-factors [n] (filter prime? (factors n)))

(defn palindrome? [n]
  (let [s (seq (str n))]
    (= s (reverse s))))

(defn square [n] (* n n))

(defmulti parse-int class)
(defmethod parse-int String [s] (Integer/parseInt s))
(defmethod parse-int Character [c] (parse-int (str c)))

(defn digits [n] (map parse-int (str n)))

(defn pythagorean? [[a b c]] (= (+ (square a) (square b)) (square c)))

(defn m-get [m [row col]] (nth (nth m row nil) col nil))

(defn factorial [n] (product (range 1 (inc n))))

(defn falling-factorial [n k] (product (take k (iterate dec n))))

(defn n-choose-k [n k] (/ (falling-factorial n k) (factorial k)))

(defn whole-nums [] (iterate inc 1))

(defn natural-nums [] (iterate inc 0))

(defn triangle-nums [] (map #(n-choose-k (inc %) 2) (whole-nums)))

(defn has? [coll x] (some #{x} coll))

(defn expt-mod-n
  ([base pow n] (expt-mod-n base pow n 1))
  ([base pow n acc]
     (let [new-acc (rem (* base acc) n)]
       (cond
	(= 0 pow) 1
	(= 1 pow) new-acc
	:else (recur base (dec pow) n new-acc)))))

(defn quadratic [n a b] (+ (square n) (* a n) b))

(defn map-vals [f m] (zipmap (keys m) (map f (vals m))))
