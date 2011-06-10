(ns p9
  (:use [euler :only (sqr find-first product timed-test)]))

(defn pythagorean? [[a b c]]
  (= (+ (sqr a) (sqr b)) (sqr c)))

(timed-test
 31875000
 (let [triplets (for [a (range 1 (/ 1000 3))
		      b (range (inc a) (/ 1000 2))]
		  [a b (- 1000 a b)])]
   (product (find-first pythagorean? triplets))))
