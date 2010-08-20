(ns p5
  (:use [utils :only (timed-test)])
  (:use [clojure.contrib.math :only (lcm)]))

(timed-test
 "Problem 5"
 232792560
 (reduce lcm (range 1 (inc 20))))
