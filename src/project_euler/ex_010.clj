(ns project-euler.ex-010
  (:require
   [clojure.math.numeric-tower :as math]
   [project-euler.ex-005 :refer [primes]]))

;; The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
;; Find the sum of all the primes below two million.

(defn sum-of-primes-under
  "Find sum of primes under value n."
  [n]
  (reduce + (take-while #(> n %) primes)))

;; (sum-of-primes-under 2000000)

