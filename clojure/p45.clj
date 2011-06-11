(ns p45
  (:use [euler :only (s-gonal? triangles timed-test)]))

(defn penta-hexa-gonal? [n]
  (and (s-gonal? 5 n) (s-gonal? 6 n)))

(timed-test
 1533776805
 (let [tri-penta-hexa-gonals (filter penta-hexa-gonal? (triangles))]
   (nth tri-penta-hexa-gonals 2)))
