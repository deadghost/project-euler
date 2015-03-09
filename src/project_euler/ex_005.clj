(ns project-euler.ex-005
  (:require
   [clojure.math.numeric-tower :as math]
   [project-euler.ex-003 :refer [is-prime? largest-prime-factor]]))

;; 2520 is the smallest number that can be divided by each of the numbers from 1
;; to 10 without any remainder.
;; What is the smallest positive number that is evenly divisible by all of the
;; numbers from 1 to 20?

;; Maybe do this with reduce?

(defn is-divisible-by?
  "True if any number in set can evenly divide n."
  [n set]
  (some #(= (mod n %) 0)
        set))

(defn numbers-under-sqrt
  "All numbers in coll under the square root of n inclusive."
  [n coll]
  (take-while #(<= (* % %) n) coll))

(def primes
  "Lazy memoized infinite lazy seq of prime numbers."
  (lazy-cat [2]
            (remove #(is-divisible-by? % (cons 2 (numbers-under-sqrt % primes))) 
                        (iterate inc (last primes)))))

(defn factorize
  "Returns vector of prime factors of n with duplicates."
  [n]
  (let [largest-prime-n (largest-prime-factor n)]
    (if (= largest-prime-n n) [n]
        (conj (factorize (/ n largest-prime-n))
              largest-prime-n))))

(defn prime-factors
  "Not used in this exercise but is useful.
  Returns list of prime factors of n without duplicates."
  [n]
  (filter #(and (is-prime? %)
                (zero? (mod n %)))
          (take n (iterate inc 1))))

(defn remove-first
  "Remove first item in collection satisfying predicate."
  [pred coll]
  (if (or (empty? coll)
          (pred (first coll)))
    (rest coll)
    (conj (remove-first pred (rest coll)) (first coll))))

(defn remove-elements
  "Remove elements of seq1 from seq2. Preserves duplicates."
  [seq1 seq2]
  (if (empty? seq1) seq2
      (recur (rest seq1)
             (remove-first #(== (first seq1) %) seq2))))

(defn union
  "Union of seq1 and seq2. Preserves duplicates."
  [seq1 seq2]
  (concat seq1 (remove-elements seq1 seq2)))

(defn smallest-multiple [n]
  (->> (map factorize (take n (iterate inc 1)))
       (reduce union)
       (apply *)))
