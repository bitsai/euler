(ns p19
  (:use [utils :only (timed-test)])
  (:import [java.util Date]))

(timed-test
 "Problem 19"
 171
 (let [start (Date. 1 0 1)
       end (Date. 101 0 1)
       millis-per-day (* 24 60 60 1000)
       times (range (.getTime start) (.getTime end) millis-per-day)
       dates (map #(Date. %) times)
       sunday-on-first? #(and (zero? (.getDay %))
			      (= 1 (.getDate %)))]
   (count (filter sunday-on-first? dates))))
