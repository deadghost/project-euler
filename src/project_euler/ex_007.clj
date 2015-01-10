(ns project-euler.ex-007
  (:require
   [clojure.math.numeric-tower :as math]
   [project-euler.ex-005 :refer [primes]]))

;; By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
;; that the 6th prime is 13.

;; What is the 10001st prime number?

(def prime-10001 (last (take 10001 primes)))
