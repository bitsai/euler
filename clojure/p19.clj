(ns p19
  (:use [euler :only (count-if timed-test)])
  (:import [java.util GregorianCalendar]))

(defn next-date [d]
  (let [new-d (.clone d)]
    (.add new-d (. GregorianCalendar DATE) 1)
    new-d))

(defn day-of-week [d]
  (.get d (. GregorianCalendar DAY_OF_WEEK)))

(defn day-of-month [d]
  (.get d (. GregorianCalendar DAY_OF_MONTH)))

(defn sunday-on-first-of-month? [d]
  (and (= (day-of-week d) (. GregorianCalendar SUNDAY))
       (= (day-of-month d) 1)))

(timed-test
 171
 (let [start (GregorianCalendar. 1901 0 1)
       end (GregorianCalendar. 2001 0 1)
       dates (take-while #(not= end %) (iterate next-date start))]
   (count-if sunday-on-first-of-month? dates)))
