(ns project-euler.ex-037
  (:require [project-euler.util :refer [digits primes prime?]]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Truncatable primes
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; The number 3797 has an interesting property. Being prime itself, it is
;; possible to continuously remove digits from left to right, and remain prime
;; at each stage: 3797, 797, 97, and 7. Similarly we can work from right to
;; left: 3797, 379, 37, and 3.
;; 
;; Find the sum of the only eleven primes that are both truncatable from left
;; to right and right to left.
;; 
;; NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.
;;==============================================================================
;; Approach
;;==============================================================================
;; The problem tells us there are only 11 such primes. Based on that, the
;; longest prime possible is 11 digits or less because with truncations 11
;; primes are generated. We'll first try a brute force approach of checking
;; every prime from the beginning.
;; [OPTIMIZATION] The first and last digit must be 2, 3, 5, or 7.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn truncate-front [n]
  (read-string (apply str (drop 1 (digits n)))))

(defn truncate-back [n]
  (read-string (apply str (drop-last (digits n)))))

(defn truncate-set [n]
  (let [digit-count (count (digits n))]
    (->> (concat (take digit-count (iterate truncate-front n))
                 (take digit-count (iterate truncate-back n)))
         (into #{}))))

(defn truncatable-prime? [n]
  (and (contains? #{2 3 5 7} (first (digits n)))
       (contains? #{2 3 5 7} (last (digits n)))
       (not (contains? (into #{} (digits n)) 0))
       (every? prime? (truncate-set n))))

(defn solve []
  (->> (filter truncatable-prime? primes)
       (take 15) ;; We don't count 2, 3, 5, 7
       (drop 4)
       (apply +)
       (time)))
