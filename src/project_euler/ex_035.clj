(ns project-euler.ex-035
  (:require [project-euler.util :refer [digits]]
            [project-euler.ex-005 :refer [primes]]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; The number, 197, is called a circular prime because all rotations of the
;; digits: 197, 971, and 719, are themselves prime.
;; 
;; There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71,
;; 73, 79, and 97.
;; 
;; How many circular primes are there below one million?
;;==============================================================================
;; Approach
;;==============================================================================
;; We'll find and store the primes under 1M. We'll then rotate all these primes
;; and check if they are circular primes.
;; We'll need a digit-rotations function that returns all rotations.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn rotate
  "Rotates the number once with the last digit now the first.
  Edge case: 0 gets rotated to the front."
  [n]
  (->> (conj (drop-last (digits n))
             (last (digits n)))
       (apply str)
       (read-string)))

(defn contains-zero? [n]
  (contains? (into #{} (digits n)) 0))

(defn rotation-set
  "Returns set of all rotations of n."
  [n]
  (into #{} (take (count (digits n))
                  (iterate rotate n))))

(def primes-under-million
  (into #{} (take-while #(< % 1000000) primes)))

(defn prime-under-million? [n]
  (contains? primes-under-million n))

(defn circular-prime-under-million?
  "General but unoptimized way to solve the problem. The solution can be
  optimized by returning the full set of rotations and not checking any already
  rotated numbers."
  [n]
  (when (and (not (contains-zero? n))
             (every? prime-under-million? (rotation-set n)))
    n))

(defn solve []
  (->> (map circular-prime-under-million? primes-under-million)
       (remove nil?)
       (count)
       (time))) 
