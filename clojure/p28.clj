(ns p28
  (:use [euler :only (sqr sum timed-test)]))

(defn corners [n]
  (for [i (range 4)]
    (- (sqr n) (* i (dec n)))))

(timed-test
 669171001
 (let [sizes (range 3 (inc 1001) 2)
       all-corners (mapcat corners sizes)]
   (apply + 1 all-corners)))
