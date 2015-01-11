(ns project-euler.ex-020
  (:require
   [clojure.math.numeric-tower :as math]
   [clojure.string :as str]))

;; n! means n × (n − 1) × ... × 3 × 2 × 1
;; For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800,
;; and the sum of the digits in the number 10!
;; is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.
;; Find the sum of the digits in the number 100!

(defn char-to-int [char]
  (- (int char) (int \0)))

(defn factorial-digit-sum [n]
  (apply + (map char-to-int (str (reduce *' (take n (iterate inc 1)))))))
