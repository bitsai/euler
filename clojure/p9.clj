(ns p9
  (:use [euler :only (product timed-test)])
  (:use [clojure.contrib.generic.math-functions :only (sqr)])
  (:use [clojure.contrib.seq :only (find-first)]))

(defn pythagorean? [[a b c]]
  (= (+ (sqr a) (sqr b)) (sqr c)))

(timed-test
 "Problem 9"
 31875000
 (let [triplets (for [a (range 1 (/ 1000 3))
		      b (range (inc a) (/ 1000 2))]
		  [a b (- 1000 a b)])]
   (product (find-first pythagorean? triplets))))
