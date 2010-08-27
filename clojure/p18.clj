(ns p18
  (:use [utils :only (m-get timed-test)]))

(def triangle [[75]
	       [95 64]
	       [17 47 82]
	       [18 35 87 10]
	       [20  4 82 47 65]
	       [19  1 23 75  3 34]
	       [88  2 77 73  7 63 67]
	       [99 65  4 28  6 16 70 92]
	       [41 41 26 56 83 40 80 70 33]
	       [41 48 72 33 47 32 37 16 94 29]
	       [53 71 44 65 25 43 91 52 97 51 14]
	       [70 11 33 28 77 73 17 78 39 68 17 57]
	       [91 71 52 38 17 14 91 43 58 50 27 29 48]
	       [63 66  4 68 89 53 67 30 73 16 69 87 40 31]
	       [ 4 62 98 27 23  9 70 98 73 93 38 53 60  4 23]])

(defn max-total [row col]
  (let [n (m-get triangle [row col])]
    (if (nil? n) 0
	(+ n (max (max-total (dec row) col)
		  (max-total (dec row) (dec col)))))))

(timed-test
 "Problem 18"
 1074
 (let [last-row (dec (count triangle))
       last-cols (range (count (triangle last-row)))
       max-totals (for [col last-cols] (max-total last-row col))]
   (apply max max-totals)))
