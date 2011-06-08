(ns p5
  (:use [euler :only (lcm timed-test)]))

(timed-test
 "Problem 5"
 232792560
 (reduce lcm (range 1 (inc 20))))
