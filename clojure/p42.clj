(ns p42
  (:use [euler :only (read-quoted-csv word-value s-gonal? count-if timed-test)]))

(timed-test
 162
 (let [words (read-quoted-csv "../data/words.txt")]
   (count-if #(s-gonal? 3 %) (map word-value words))))
