(ns project-euler.ex-030
  (:require [clojure.math.numeric-tower :refer [expt]]
            [project-euler.util :refer [digits]]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Surprisingly there are only three numbers that can be written as the sum of
;; fourth powers of their digits:
;; 
;; 1634 = 1^4 + 6^4 + 3^4 + 4^4
;; 8208 = 8^4 + 2^4 + 0^4 + 8^4
;; 9474 = 9^4 + 4^4 + 7^4 + 4^4
;; 
;; As 1 = 1^4 is not a sum it is not included.
;; 
;; The sum of these numbers is 1634 + 8208 + 9474 = 19316.
;; 
;; Find the sum of all the numbers that can be written as the sum of fifth
;; powers of their digits.
;;==============================================================================
;; Approach
;;==============================================================================
;; The minimum number of digits the numbers can have is 2 because single digits
;; do not sum.
;; The maximum number of digits is n when the digit count of (9^5)n is equal to
;; n.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def maximum-digit-count
  "It's 6 digits."
  (some identity (map (fn [n] (when (= (count (str (* (expt 9 5) n))) n) n))
                      (iterate inc 1))))

(def possible-numbers
  (range 10 999999))

(defn fifth-power-sum [n]
  (apply + (map #(expt % 5) (digits n))))

(defn solve []
  (->> (map #(when (= (fifth-power-sum %) %) %) possible-numbers)
       (remove nil?)
       (apply +)))
