(ns project-euler.ex-043
  (:require
   [clojure.edn :as edn]
   [clojure.math.combinatorics :as c]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Sub-string divisibility
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; The number, 1406357289, is a 0 to 9 pandigital number because it is made up
;; of each of the digits 0 to 9 in some order, but it also has a rather
;; interesting sub-string divisibility property.
;;
;; Let d1 be the 1st digit, d2 be the 2nd digit, and so on. In this way, we note
;; the following:
;;
;; d2d3d4=406 is divisible by 2
;; d3d4d5=063 is divisible by 3
;; d4d5d6=635 is divisible by 5
;; d5d6d7=357 is divisible by 7
;; d6d7d8=572 is divisible by 11
;; d7d8d9=728 is divisible by 13
;; d8d9d10=289 is divisible by 17
;; 
;; Find the sum of all 0 to 9 pandigital numbers with this property.
;;==============================================================================
;; Approach #0
;;==============================================================================
;; Generate all integers from 0 to 9876543210.
;; Filter for pandigital numbers.
;; Filter for given divisibility rules.
;;
;; This is too slow before we even get to divisibility rules.
;;==============================================================================
;; Approach #1
;;==============================================================================
;; Note: I misunderstood the problem originally and thought it counted numbers
;; like 10 and 102. The problem only counts 10 digit integers.
;; Generate all 0 to 9 pandigital numbers.
;; Filter for given divisibility rules.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def all-pandigitals
  "All 0 to 9 pandigital integers."
  (->> (c/permutations (range 0 10))
       (remove #(zero? (first %)))
       (map (partial apply str))
       (map edn/read-string)))

;; (count all-pandigitals) => 3265920

(defn subs-divisibility
  "Checks the integer from start-digit to end-digit of x is divisible by y."
  [x start-digit end-digit y]
  (->> (subs (str x) (dec start-digit) end-digit)
       (drop-while #(= \0 %)) ; rm starting 0s
       (apply str)
       (edn/read-string)
       (#(zero? (mod % y)))))

;; (subs-divisibility 1406357289 2 4 2) => true
;; (subs-divisibility 1406357289 3 5 3) => true
;; (subs-divisibility 1406357289 8 10 17) => true

(defn solve []
  (->> all-pandigitals
       (filter #(subs-divisibility % 2 4 2))
       (filter #(subs-divisibility % 3 5 3))
       (filter #(subs-divisibility % 4 6 5))
       (filter #(subs-divisibility % 5 7 7))
       (filter #(subs-divisibility % 6 8 11))
       (filter #(subs-divisibility % 7 9 13))
       (filter #(subs-divisibility % 8 10 17))
       (reduce +)))

;; "Elapsed time: 46702.693969 msecs"
;; 16695334890
