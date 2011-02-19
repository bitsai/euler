(ns utils
  (:use [clojure.contrib.generic.math-functions :only (round sqr sqrt)])
  (:require [clojure.string :as str]))

(defn expt [base pow]
  (reduce * (repeat pow base)))

(defn fibs []
  (let [f (fn [[a b]] [b (+ a b)])]
    (map first (iterate f [0 1]))))

(defmacro timed-test [name answer code]
  `(do
     (println ~name)
     (let [my-answer# (time ~code)
	   status# (if (= my-answer# ~answer) "OK" "FAIL")]
       (println "[ " status# " ]"))))

(defn multiple? [m n]
  (zero? (rem m n)))

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
	pairs (for [i (range 1 root) :when (multiple? n i)] [i (/ n i)])
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
  (if (>= n 2)
    (let [root (sqrt n)
	  two-to-sqrt (conj (range 3 (inc root) 2) 2)]
      (not-any? #(multiple? n %) two-to-sqrt))))

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

(defn digits [n]
  (loop [n n
	 digits-so-far '()]
    (if (< n 10)
      (conj digits-so-far n)
      (recur (quot n 10)
	     (conj digits-so-far (rem n 10))))))

(defn pythagorean? [[a b c]]
  (= (+ (sqr a) (sqr b)) (sqr c)))

(defn factorial [n]
  (product (range 1 (inc n))))

(defn falling-factorial [n k]
  (product (take k (iterate dec n))))

(defn n-choose-k [n k]
  (/ (falling-factorial n k) (factorial k)))

(defn triangle-nums []
  (map #(n-choose-k (inc %) 2) (next (range))))

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
    (= n (round n))))
