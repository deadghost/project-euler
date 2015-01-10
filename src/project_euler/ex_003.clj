(ns project-euler.ex-003
  (:require
   [clojure.math.numeric-tower :as math]))

;; The prime factors of 13195 are 5, 7, 13 and 29.
;; What is the largest prime factor of the number 600851475143 ?

;; TODO Cache prime numbers in a lazy infinite seq.

(defn find-smallest-factor
  "Find smallest factor of n."
  [n]
  (first (for [divisor (iterate inc 2)
               :when (or (zero? (mod n divisor))
                         (> divisor (math/floor (/ n 2))))]
           (if (> divisor (math/floor (/ n 2)))
             n
             divisor))))

(defn is-prime?
  [n]
  (if (= (find-smallest-factor n) n)
    n
    nil))

(defn largest-prime-factor [n]
  (first (for [i (iterate dec (math/floor (math/sqrt n)))
               :when (and (== (mod n i) 0)
                          (or (is-prime? i)
                              (== i 1)))]
           (if (== i 1)
             n
             i))))
