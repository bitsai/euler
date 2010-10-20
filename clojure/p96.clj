(ns p96
  (:use [clojure.java.io :only (reader)])
  (:use [clojure.string :only (join split-lines)])
  (:use [utils :only (sum timed-test)]))

(defn cross [A B]
  (for [a A, b B] (str a b)))

(defn all? [coll]
  (every? #(not (false? %)) coll))

(defn in? [x coll]
  (some #{x} coll))

(defn set-values! [values s s-values]
  (dosync (alter values #(assoc-in % [s] s-values))))

(defn copy [reference]
  (ref @reference))

(def digits "123456789")
(def rows "ABCDEFGHI")
(def cols digits)
(def squares (cross rows cols))
(def unitlist
  (concat
   (for [c cols] (cross rows (str c)))
   (for [r rows] (cross (str r) cols))
   (for [rs (partition 3 rows) cs (partition 3 cols)] (cross rs cs))))
(def units
  (into {} (for [s squares] [s (filter #(in? s %) unitlist)])))
(def peers
  (into {} (for [s squares] [s (set (remove #{s} (flatten (units s))))])))

(declare assign eliminate helper-1 helper-2)

(defn assign [values s d]
  (let [other-values (remove #{d} (@values s))]
    (if (all? (for [d2 other-values] (eliminate values s d2)))
      values
      false)))

(defn eliminate [values s d]
  (if-not (in? d (@values s))
    values
    (let [other-values (remove #{d} (@values s))]
      (set-values! values s other-values)
      (cond
       (false? (helper-1 values s d)) false
       (false? (helper-2 values s d)) false
       :else values))))

(defn helper-1 [values s d]
  (case (count (@values s))
	0 false
	1 (let [d2 (first (@values s))]
	    (if-not (all? (for [s2 (peers s)] (eliminate values s2 d2)))
	      false
	      true))
	true))

(defn helper-2 [values s d]
  (all? (for [u (units s)]
	  (let [dplaces (filter #(in? d (@values %)) u)]
	    (case (count dplaces)
		  0 false
		  1 (if-not (assign values (first dplaces) d)
		      false
		      true)  
		  true)))))

(defn grid-values [grid]
  (zipmap squares grid))

(defn parse-grid [grid]
  (let [values (ref (into {} (for [s squares] [s digits])))]
    (if-not (all? (for [[s d] (grid-values grid) :when (in? d digits)]
		    (assign values s d)))
      false
      values)))

(defn display [values]
  (let [lines (for [r rows]
		(let [strs (for [c cols] (apply str (@values (str r c))))]
		  (join " " strs)))]
    (println (join "\n" lines))))

(defn search [values]
  (cond
   (false? values) false
   (all? (for [s squares] (= 1 (count (@values s))))) values
   :else (let [unfilled-squares (filter #(< 1 (count (@values %))) squares)
	       s (apply min-key #(count (@values %)) unfilled-squares)]
	   (some #(search (assign (copy values) s %)) (@values s)))))

(defn solve [grid]
  (search (parse-grid grid)))

(defn top-left-num [values]
  (let [get-value #(first (@values %))
	s (str (get-value "A1") (get-value "A2") (get-value "A3"))]
    (Integer/parseInt s)))

(timed-test
 "Problem 96"
 24702
 (with-open [rdr (reader "../data/sudoku.txt")]
   (let [grid-lines (filter #(re-matches #"^\d+$" %) (line-seq rdr))
	 grids (map #(apply concat %) (partition 9 grid-lines))
	 solutions (map solve grids)]
     (sum (map top-left-num solutions)))))
