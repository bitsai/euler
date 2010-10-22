(ns p45
  (:use [clojure.contrib.generic.math-functions :only (round sqrt)])
  (:use [utils :only (sq triangle-nums timed-test)]))

(defn s-gonal? [x s]
  (let [a (* (- (* 8 s) 16) x)
	b (sq (- s 4))
	numerator (- (+ (sqrt (+ a b)) s) 4)
	denominator (- (* 2 s) 4)
	n (/ numerator denominator)]
    (== n (round n))))

(timed-test
 "Problem 45"
 1533776805
 (let [triple-gonal (filter
		     #(and (s-gonal? % 5) (s-gonal? % 6))
		     (triangle-nums))]
   (nth triple-gonal 2)))
