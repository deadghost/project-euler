(ns project-euler.ex-007
  (:require
   [clojure.math.numeric-tower :as math]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 10001st prime
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
;; that the 6th prime is 13.
;;
;; What is the 10001st prime number?
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; I want to use the seive of Eratosthenes except it's used for finding all
;; primes up to a given limit. We're not given the upper limit in this problem.

(defn is-divisible-by?
  "True if any integer in intseq can divide n without remainder."
  [n intseq]
  (some #(zero? (mod n %)) intseq))

(defn numbers-under-sqrt
  "All integers in intseq under the square root of n inclusive."
  [n intseq]
  (take-while #(<= (* % %) n) intseq))

(def primes
  "Lazy memoized infinite lazy seq of prime numbers. These are held in memory.
  A number is prime if it is not divisible by its square root and integers lower
  than its square root."
  (lazy-cat [2]
            (remove #(is-divisible-by? % (cons 2 (numbers-under-sqrt % primes))) 
                    (iterate inc (last primes)))))

(def prime-10001 (last (take 10001 primes)))
