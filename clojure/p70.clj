(ns p70
  (:use [euler :only (prime-sieve sqrt min-by timed-test)]))

;; n / phi(n) for n < N is min when n = product of 2 primes near sqrt(N)
(defn n [p1 p2]
  (* p1 p2))

;; If n = product of primes p1 and p2, phi(n) is simply (p1-1) * (p2-1)
(defn phi [p1 p2]
  (* (dec p1) (dec p2)))

(defn permutation? [x y]
  (= (sort (str x)) (sort (str y))))

(defn phi-permutation-of-n? [[p1 p2]]
  (permutation? (phi p1 p2) (n p1 p2)))

(defn n-phi-ratio [[p1 p2]]
  (/ (n p1 p2) (phi p1 p2)))

(timed-test
 8319823
 (let [N 10000000
       primes (vec (prime-sieve (* 2 (sqrt N))))
       ns (for [i (range (count primes))
                j (range i (count primes))
                :when (< (n (primes i) (primes j)) N)]
            [(primes i) (primes j)])
       [p1 p2] (min-by n-phi-ratio (filter phi-permutation-of-n? ns))]
   (n p1 p2)))
