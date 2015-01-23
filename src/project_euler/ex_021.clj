(ns project-euler.ex-021
  (:require
   [clojure.math.numeric-tower :as math]
   [project-euler.util :refer [take-until]]
   [project-euler.ex-002 :refer [fib-seq]]
   [project-euler.ex-005 :refer [factorize]]
   [project-euler.ex-023 :refer [proper-divisors]]))

;; Let d(n) be defined as the sum of proper divisors of n (numbers less than n
;; which divide evenly into n). If d(a) = b and d(b) = a, where a ≠ b, then a
;; and b are an amicable pair and each of a and b are called amicable numbers.

;; For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44,
;; 55 and 110; therefore d(220) = 284. The proper divisors of 284 are 1, 2, 4,
;; 71 and 142; so d(284) = 220.

;; Evaluate the sum of all the amicable numbers under 10000.

(defn amicable?
  "Return n if number is amicable."
  [n]
  (if (= n 1) nil ; 1 has no proper divisors
      (let [n2 (apply + (proper-divisors n))]
        (when (and (= n (apply + (proper-divisors n2)))
                   (not (= n n2)))
          n))))

(def amicable-numbers
  "Lazy infinite memoized sequence of amicable numbers."
  (filter amicable? (iterate inc 1)))

(apply + (take-while #(< % 10000) amicable-numbers))
