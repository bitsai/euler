(ns p12
  (:use [euler :only (factors naturals find-first timed-test)]))

;; From Project Euler solution pdf
(defn count-nth-tri-num-factors [n]
  (let [D (fn [x] (count (factors x)))]
    (if (even? n)
      (* (D (/ n 2)) (D (inc n)))
      (* (D n) (D (/ (inc n) 2))))))

(defn nth-tri-num [n]
  (* n (/ (inc n) 2)))

(timed-test
 76576500
 (let [n (find-first #(> (count-nth-tri-num-factors %) 500) (naturals))]
   (nth-tri-num n)))
