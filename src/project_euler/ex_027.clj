(ns project-euler.ex-027
  (:require
   [project-euler.ex-003 :refer [is-prime?]]
   [project-euler.ex-005 :refer [primes]]
   [clojure.math.combinatorics :as c]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Euler discovered the remarkable quadratic formula: n^2+n+41
;;
;; It turns out that the formula will produce 40 primes for the consecutive
;; integer values 0≤n≤39.
;; However, when n=40,40^2+40+41=40(40+1)+41 is divisible by 41, and certainly
;; when n=41,41^2+41+41 is clearly divisible by 41.
;;
;; The incredible formula n^2−79n+1601
;; was discovered, which produces 80 primes for the consecutive values 0≤n≤79.
;;
;; The product of the coefficients, −79 and 1601, is −126479.
;;
;; Considering quadratics of the form: n^2+an+b, where |a|<1000 and |b|≤1000
;; where |n| is the modulus/absolute value of n e.g. |11|=11 and |−4|=4
;; Find the product of the coefficients, a and b, for the quadratic expression
;; that produces the maximum number of primes for consecutive values of n,
;; starting with n=0.
;;==============================================================================
;; Approach
;;==============================================================================
;; b must be prime because when n=0, the formula is b.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn quadratic [a b n]
  (+ (* n n)
     (* a n)
     b))

(def possible-b-values
  "b must be a prime number."
  (take-while #(<= % 1000) primes))

(def possible-a-values
  "-999 to 999"
  (take-while #(< % 1000) (iterate inc -999)))

(defn prime-count
  "Returns a b and max n where the result is prime."
  ([a b] (prime-count a b 0))
  ([a b n]
   (let [result (quadratic a b n)]
     (if (is-prime? result)
       (recur a b (inc n))
       [a b n]))))

(defn solve []
  (->> (map #(apply prime-count %)
            (c/cartesian-product possible-a-values possible-b-values))
       (apply max-key last)
       (drop-last)
       (apply *)))
;; -59231
