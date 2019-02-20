(ns project-euler.ex-003
  (:require
   [clojure.math.numeric-tower :as math]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Largest prime factor
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; The prime factors of 13195 are 5, 7, 13 and 29.
;; What is the largest prime factor of the number 600851475143 ?
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Solution 1: Trial Division to Square Root of n
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; A common fallacy here is believing that the largest prime factor is less than
;; or equal to the square root of n unless n is prime. (I believed this the
;; first time I did this problem). This can be disproven with a counterexample.
;; When n is 15,the largest prime factor is 5. The sqrt of 15 is ~3.87.
;; 5 > ~3.87.
;; It is however possible to reduce the solution space to around sqrt(n).
;; We prime factorize n to factors <= sqrt(n). We divide n by the product of
;; the factors and we arrive at the largest prime factor. There can only be
;; 0 or 1 prime factors > sqrt(n).
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn largest-prime-factor
  ([n] (largest-prime-factor n n 2 (math/sqrt n) [1]))
  ([n dividend divisor sqrtn acc]
   (cond (> divisor sqrtn)
         ;; Our largest prime factor.
         (let [product (apply * acc)]
           (if (= product n)
             (last acc) ; 0 prime factors > sqrt(n)
             (/ n (apply * acc))))
         (zero? (mod dividend divisor))
         ;; Evenly divides, try dividing with same divisor.
         (recur n (/ dividend divisor) divisor sqrtn (conj acc divisor))
         :else ;; Does not evenly divide, increment divisor.
         (recur n dividend (inc divisor) sqrtn acc))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Other Methods
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Quadratic Sieve: Fastest for integers ~100 digits.
;; General Number Field: Fastest for integers > 100 digits.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
