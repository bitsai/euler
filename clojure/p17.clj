(ns p17
  (:use [utils :only (digits sum timed-test)]))

(def ones {1 " one "
	   2 " two "
	   3 " three "
	   4 " four "
	   5 " five "
	   6 " six "
	   7 " seven "
	   8 " eight "
	   9 " nine "})

(def teens {0 " ten "
	    1 " eleven "
	    2 " twelve "
	    3 " thirteen "
	    4 " fourteen "
	    5 " fifteen "
	    6 " sixteen "
	    7 " seventeen "
	    8 " eighteen "
	    9 " nineteen "})

(def tens {2 " twenty "
	   3 " thirty "
	   4 " forty "
	   5 " fifty "
	   6 " sixty "
	   7 " seventy "
	   8 " eighty "
	   9 " ninety "})

(defn not-zero? [digit]
  (and (not (nil? digit))
       (not (zero? digit))))

(defn digit-to-words [digit suffix]
  (if (not-zero? digit)
    (let [word (ones digit)]
      (str word suffix))))

(defn digits-to-words [ten one]
  (if (= ten 1) (teens one)
      (str (tens ten) (ones one))))

(defn add-and [thousand hundred ten one]
  (if (and (or (not-zero? thousand) (not-zero? hundred))
	   (or (not-zero? ten) (not-zero? one)))
    " and "))

(defn num-to-words [n]
  (let [[one ten hundred thousand] (reverse (digits n))]
    (str
     (digit-to-words thousand " thousand ")
     (digit-to-words hundred " hundred ")
     (add-and thousand hundred ten one)
     (digits-to-words ten one))))

(defn count-letters [n]
  (let [not-space? (complement #{\space})]
    (count (filter not-space? (num-to-words n)))))

(timed-test
 "Problem 17"
 21124
 (sum (map count-letters (range 1 (inc 1000)))))
