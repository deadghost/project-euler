(ns project-euler.ex-005
  (:require
   [clojure.math.numeric-tower :as math]
   [project-euler.ex-003 :refer [is-prime?]]))

;; 2520 is the smallest number that can be divided by each of the numbers from 1
;; to 10 without any remainder.
;; What is the smallest positive number that is evenly divisible by all of the
;; numbers from 1 to 20?

;; Use union.

(defn is-divisible-by?
  "True if any number in set can evenly divide n."
  [n set]
  (some #(= (mod n %) 0)
        set))

(def primes
  "Lazy memoized infinite lazy seq of prime numbers."
  (lazy-cat [2] (remove #(is-divisible-by? % primes) 
                        (iterate inc (last primes)))))

(defn factorize [n]
  ()
  (if (is-prime? n) n
      (conj )))

(defn prime-factors [n]
  (filter #(and (is-prime? %)
                (zero? (mod n %)))
          (take n (iterate inc 1))))

(def prime-factors-to-20
  (->> (map prime-factors (take 20 (iterate inc 1)))
       (apply concat)
       (set)
       (apply *)))
