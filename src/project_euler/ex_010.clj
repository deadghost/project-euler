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

;; We have an upper limit so unlike ex-007 we can use the sieve of Eratosthenes.
;; Our previous solution is kinda sorta acceptable in speed so we just use that.

(defn sum-of-primes-under
  "Find sum of primes under value n."
  [n]
  (reduce + (take-while #(> n %) primes)))

(defn solve []
  (sum-of-primes-under 2000000))
