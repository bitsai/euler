(ns euler
  (:require [clojure.string :as str])
  (:require [clojure.contrib.math :as math]))

;; Testing macro
(defmacro timed-test [answer expr]
  `(do
     (print (format "%-5s" (ns-name *ns*)))
     (let [result# (time ~expr)]
       (if (not= result# ~answer)
         (println "*** FAIL ***")))))

;; Basic functions
(defn abs [n]
  (math/abs n))

(defn sqr [n]
  (* n n))

(defn sqrt [n]
  (math/sqrt n))

(defn expt [base pow]
  (math/expt base pow))

(defn ceil [n]
  (math/ceil n))

(defn digits [n]
  (map #(Integer/parseInt (str %)) (str n)))

;; Sequence functions
(defn sum [coll]
  (reduce + coll))

(defn sum-if [pred coll]
  (sum (filter pred coll)))

(defn max-of [coll]
  (if (seq coll) (reduce max coll)))

(defn max-key [k & xs]
  (first (reduce (fn [x y] (if (> (second x) (second y)) x y))
                 (map (juxt identity k) xs))))

(defn max-by [k coll]
  (apply max-key k coll))

(defn min-key [k & xs]
  (first (reduce (fn [x y] (if (< (second x) (second y)) x y))
                 (map (juxt identity k) xs))))

(defn min-by [k coll]
  (apply min-key k coll))

(defn product [coll]
  (reduce * coll))

(defn find-first [pred coll]
  (first (filter pred coll)))

(defn count-if [pred coll]
  (count (filter pred coll)))

;; Predicates
(defn divides? [a b]
  (zero? (rem a b)))

(defn prime? [n]
  (cond
   (< n 2) false
   (= n 2) true
   :else (let [root (sqrt n)
               xs (cons 2 (range 3 (inc root) 2))]
           (not-any? #(divides? n %) xs))))

(defn palindrome? [n]
  (let [s (str n)]
    (= (seq s) (reverse s))))

(defn s-gonal? [s n]
  (let [a (* (- (* 8 s) 16) n)
	b (sqr (- s 4))
	numerator (- (+ (sqrt (+ a b)) s) 4)
	denominator (- (* 2 s) 4)]
    (integer? (/ numerator denominator))))

;; Sequences
(defn fibs []
  (let [f (fn [[a b]] [b (+ a b)])]
    (map first (iterate f [0 1]))))

(defn naturals []
  (rest (range)))

(defn triangles []
  (reductions + (naturals)))

(defn primes []
  (filter prime? (naturals)))

;; en.wikipedia.org/wiki/Sieve_of_Eratosthenes, with all improvements
(defn prime-sieve [n]
  (when (> n 2)
    (let [n (ceil n)
          arr (boolean-array n true)]
      ;; Cross off 0, 1, even nums from 4 up to n
      (aset arr 0 false)
      (aset arr 1 false)
      (doseq [i (range 4 n 2)]
        (aset arr i false))
      ;; For p = (3, 5, ..., (sqrt n))
      (doseq [p (range 3 (sqrt n) 2)]
        ;; If p is not already crossed off
        (if (aget arr p)
          ;; Cross off odd multiples from p^2 up to n
          (doseq [multiple (range (sqr p) n (* 2 p))]
            (aset arr multiple false))))
      (filter #(aget arr %) (range 2 n)))))

;; Factorization
(defn factors [n]
  (let [root (sqrt n)
	pairs (for [i (range 1 root) :when (divides? n i)]
                [(/ n i) i])
	factors (reduce into [] pairs)]
    (if (divides? n root)
      (conj factors root)
      factors)))

(defn prime-factors [n]
  (filter prime? (factors n)))

(defn proper-divisors [n]
  (rest (factors n)))

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

;; Text functions
(defn char-value [c]
  (- (int c) 64))

(defn word-value [s]
  (sum (map char-value s)))

(defn read-quoted-csv [f]
  (-> (str/trim (slurp f))
      (str/replace "\"" "")
      (str/split #",")))

;; Permutations
;; en.wikipedia.org/wiki/Permutation, Pandita's method
(defn next-permutation [v]
  (let [len (count v)]
    ;; find k
    (if-let [k (loop [i (- len 2)]
                 (cond (neg? i) nil
                       (< (v i) (v (inc i))) i
                       :else (recur (dec i))))]
      ;; find l
      (let [l (loop [i (dec len)]
		(cond (< (v k) (v i)) i
                      :else (recur (dec i))))]
        ;; swap a[k] and a[l], reverse sequence from a[k+1] to a[n]
        (loop [v (assoc v k (v l) l (v k))
               i (inc k)
               j (dec len)]
	  (if (< i j)
	    (recur (assoc v i (v j) j (v i))
                   (inc i)
                   (dec j))
	    v))))))

(defn permutations [& xs]
  (let [v (vec (sort xs))]
    (take-while identity (iterate next-permutation v))))
