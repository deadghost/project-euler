(ns project-euler.ex-006
  (:require
   [clojure.math.numeric-tower :as math]))

;; The sum of the squares of the first ten natural numbers is,
;; 1^2 + 2^2 + ... + 10^2 = 385

;; The square of the sum of the first ten natural numbers is,
;; (1 + 2 + ... + 10)^2 = 552 = 3025

;; Hence the difference between the sum of the squares of the first ten natural
;; numbers and the square of the sum is 3025 − 385 = 2640.

;; Find the difference between the sum of the squares of the first one hundred
;; natural numbers and the square of the sum.

(def sum-sq-difference
  (- (math/expt (apply + (take 100 (iterate inc 1))) 2)
     (apply + (map #(math/expt % 2) (take 100 (iterate inc 1))))))
