(ns project-euler.ex-010
  (:require
   [clojure.math.numeric-tower :as math]
   [project-euler.ex-007 :refer [primes]]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Summation of primes
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
;; Find the sum of all the primes below two million.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Solution 1
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; We have an upper limit so unlike ex-007 we can use the sieve of Eratosthenes.
;; Our previous solution is kinda sorta acceptable in speed so we just use that.

(defn sum-of-primes-under
  "Find sum of primes under value n."
  [n]
  (reduce + (take-while #(< % n) primes)))

(defn solve []
  (sum-of-primes-under 2000000))

;; (time (solve))
;; "Elapsed time: 12897.95614 msecs"
;; => 142913828922

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Solution 2: Sieve of Eratosthenes
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn sieve
  "Set every nth element to false. The first element is false."
  ([xs n] (sieve xs n []))
  ([xs n acc]
   (if (seq xs)
     (recur (drop n xs) n
            (into acc (conj (take (dec n) (rest xs)) false)))
     acc)))

(defn sieve-of-eratosthenes
  ([n] (sieve-of-eratosthenes (take (dec n) (iterate inc 2)) []))
  ([xs acc]
   (cond
     ;; integers exhausted, return all primes
     (empty? xs) acc
     ;; found a prime, continue
     (first xs)
     (recur (sieve xs (first xs))
            (conj acc (first xs)))
     ;; not a prime, continue
     :else (recur (rest xs) acc))))

;; (time (reduce + (sieve-of-eratosthenes 20000)))
;; "Elapsed time: 7217.178992 msecs"
;; This implementation is impractically slow.
