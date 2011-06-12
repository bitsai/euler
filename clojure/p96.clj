(ns p96
  (:use [euler :only (min-by sum timed-test)])
  (:require [clojure.string :as str]))

(defn cross [as bs]
  (for [a as, b bs] (str a b)))

(defn all? [coll]
  (every? identity coll))

(defn in? [x coll]
  (some #{x} coll))

(defn set-values! [values s s-values]
  (swap! values assoc s s-values))

(defn copy [a]
  (atom @a))

(def digits "123456789")
(def rows "ABCDEFGHI")
(def cols digits)
(def squares (cross rows cols))
(def unitlist (concat (for [c cols]
                        (cross rows (str c)))
                      (for [r rows]
                        (cross (str r) cols))
                      (for [rs (partition 3 rows), cs (partition 3 cols)]
                        (cross rs cs))))
(def units (into {} (for [s squares]
                      [s (filter #(in? s %) unitlist)])))
(def peers (into {} (for [s squares]
                      [s (set (remove #{s} (flatten (units s))))])))

(declare assign eliminate helper-1 helper-2)

(defn assign [values s d]
  (let [other-values (remove #{d} (@values s))]
    (if (all? (for [d2 other-values] (eliminate values s d2)))
      values)))

(defn eliminate [values s d]
  (if-not (in? d (@values s))
    values
    (let [other-values (remove #{d} (@values s))]
      (set-values! values s other-values)
      (cond
       (not (helper-1 values s d)) false
       (not (helper-2 values s d)) false
       :else values))))

(defn helper-1 [values s d]
  (case (count (@values s))
	0 false
	1 (let [d2 (first (@values s))]
	    (all? (for [s2 (peers s)] (eliminate values s2 d2))))
	true))

(defn helper-2 [values s d]
  (all? (for [u (units s)]
	  (let [dplaces (filter #(in? d (@values %)) u)]
	    (case (count dplaces)
		  0 false
		  1 (assign values (first dplaces) d)
		  true)))))

(defn grid-values [grid]
  (zipmap squares grid))

(defn parse-grid [grid]
  (let [values (atom (into {} (for [s squares] [s digits])))]
    (if (all? (for [[s d] (grid-values grid) :when (in? d digits)]
                (assign values s d)))
      values)))

(defn search [values]
  (cond
   (not values) false
   (all? (for [s squares] (= (count (@values s)) 1))) values
   :else (let [unfilled (filter #(> (count (@values %)) 1) squares)
	       s (min-by #(count (@values %)) unfilled)]
	   (some #(search (assign (copy values) s %)) (@values s)))))

(defn solve [grid]
  (search (parse-grid grid)))

(defn top-left-num [values]
  (let [value-of (fn [s] (first (@values s)))
	num-str (str (value-of "A1") (value-of "A2") (value-of "A3"))]
    (Integer/parseInt num-str)))

(timed-test
 24702
 (let [lines (str/split-lines (slurp "../data/sudoku.txt"))
       grid-lines (filter #(re-matches #"^\d{9}$" %) lines)
       grids (map str/join (partition 9 grid-lines))
       solutions (map solve grids)]
   (sum (map top-left-num solutions))))
