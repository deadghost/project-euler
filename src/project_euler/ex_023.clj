(ns project-euler.ex-023
  (:require
   [clojure.math.numeric-tower :as math]
   [project-euler.util :refer [take-until]]
   [project-euler.ex-002 :refer [fib-seq]]
   [project-euler.ex-005 :refer [factorize]]))

;; A perfect number is a number for which the sum of its proper divisors is
;; exactly equal to the number. For example, the sum of the proper divisors of
;; 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 28 is a perfect number.

;; A number n is called deficient if the sum of its proper divisors is less than
;; n and it is called abundant if this sum exceeds n.

;; As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest
;; number that can be written as the sum of two abundant numbers is 24. By
;; mathematical analysis, it can be shown that all integers greater than 28123
;; can be written as the sum of two abundant numbers. However, this upper limit
;; cannot be reduced any further by analysis even though it is known that the
;; greatest number that cannot be expressed as the sum of two abundant numbers
;; is less than this limit.

;; Find the sum of all the positive integers which cannot be written as the sum
;; of two abundant numbers.

(defn permutate
  "Returns set of every way to multiply together elements of coll1 and coll2."
  [coll1 coll2]
  (set (mapcat (fn [n] (map #(* n %) coll2)) coll1)))

(defn exponents-to
  "Returns values of (expt base 0) (expt base 1) ... (expt base exponent)."
  [base exponent]
  (map #(math/expt base %) (take (inc exponent) (iterate inc 0))))

(defn all-divisors
  "Returns set of all divisors."
  [n]
  (->> (frequencies (factorize n))
       (map #(apply exponents-to %))
       (reduce permutate)))

(defn proper-divisors
  "Proper divisors of 28 are 1, 2, 4, 7, 14.
  Returns set of all proper divisors."
  [n]
  (set (remove #{n} (all-divisors n))))

(defn abundant-number?
  "Is n an abundant number?"
  [n]
  (if (> (apply + (proper-divisors n)) n)
    n nil))

(def abundant-numbers
  "Lazy memoized sequence of abundant numbers."
  (filter abundant-number? (iterate inc 1)))

(defn abundant-sum?
  "Returns n if n is the sum of two abundant numbers."
  [n]
  (some #(when (abundant-number? (- n %)) n)
        (take-while #(< % n) abundant-numbers)))

(def abundant-sums
  "Lazy memoized sequence of abundant sums."
  (filter abundant-sum? (iterate inc 1)))

(defn nonabundant-sum?
  "Returns n if n is not the sum of two abundant numbers."
  [n]
  (when (not (abundant-sum? n)) n))

(def nonabundant-sums
  "Lazy memoized sequence of nonabundant sums."
  (filter nonabundant-sum? (take 28123 (iterate inc 1))))

(reduce +' nonabundant-sums)
