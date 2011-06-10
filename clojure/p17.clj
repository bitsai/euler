(ns p17
  (:use [euler :only (digits count-if sum timed-test)]))

(def ones-words {1 " one "
                 2 " two "
                 3 " three "
                 4 " four "
                 5 " five "
                 6 " six "
                 7 " seven "
                 8 " eight "
                 9 " nine "})

(def teens-words {0 " ten "
                  1 " eleven "
                  2 " twelve "
                  3 " thirteen "
                  4 " fourteen "
                  5 " fifteen "
                  6 " sixteen "
                  7 " seventeen "
                  8 " eighteen "
                  9 " nineteen "})

(def tens-words {2 " twenty "
                 3 " thirty "
                 4 " forty "
                 5 " fifty "
                 6 " sixty "
                 7 " seventy "
                 8 " eighty "
                 9 " ninety "})

(defn digit-to-words [digit suffix]
  (if (pos? digit)
    (str (ones-words digit) suffix)))

(defn digits-to-words [tens-digit ones-digit]
  (if (= tens-digit 1)
    (teens-words ones-digit)
    (str (tens-words tens-digit) (ones-words ones-digit))))

(defn add-and [thousands-digit hundreds-digit tens-digit ones-digit]
  (if (and (or (pos? thousands-digit) (pos? hundreds-digit))
	   (or (pos? tens-digit) (pos? ones-digit)))
    " and "))

(defn num-to-words [n]
  (let [s (format "%04d" n)
        [thousands-digit hundreds-digit tens-digit ones-digit] (digits s)]
    (str
     (digit-to-words thousands-digit " thousand ")
     (digit-to-words hundreds-digit " hundred ")
     (add-and thousands-digit hundreds-digit tens-digit ones-digit)
     (digits-to-words tens-digit ones-digit))))

(defn count-letters [n]
  (let [not-space? (complement #{\space})]
    (count-if not-space? (num-to-words n))))

(timed-test
 21124
 (sum (map count-letters (range 1 (inc 1000)))))
