(ns p29
  (:use [euler :only (expt timed-test)]))

(timed-test
 9183
 (let [expts (for [a (range 2 101)
                   b (range 2 101)]
               (expt a b))]
   (count (distinct expts))))
