(ns project-euler.ex-032
  (:require [project-euler.util :refer [digits]]
            [clojure.math.numeric-tower :refer [sqrt floor]]
            [clojure.math.combinatorics :as c]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; We shall say that an n-digit number is pandigital if it makes use of all the
;; digits 1 to n exactly once; for example, the 5-digit number, 15234, is 1
;; through 5 pandigital.
;; 
;; The product 7254 is unusual, as the identity, 39 × 186 = 7254, containing
;; multiplicand, multiplier, and product is 1 through 9 pandigital.
;; 
;; Find the sum of all products whose multiplicand/multiplier/product identity
;; can be written as a 1 through 9 pandigital.
;; HINT: Some products can be obtained in more than one way so be sure to only
;; include it once in your sum.
;;==============================================================================
;; Approach
;;==============================================================================
;; We need to reduce the solution space by finding the possible a and b number
;; of digits where the sum of digits of a, b, and c is 9.
;; From there we brute force the solution.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn min-max-c
  "Returns vector of lowest and highest digit count of c given digit counts of
  a and b. Used to reduce the solution space."
  [a b]
  [(count (str (* (read-string (apply str (take a "123456789")))
                  (read-string (apply str (take b (drop a "123456789")))))))
   (count (str (* (read-string (apply str (take a "987654321")))
                  (read-string (apply str (take b (drop a "987654321")))))))])

(def possible-a-b-digit-counts
  "List of list of a/b digit count pairs where it's possible for the counts
  of a, b, and c to equal 9. ((1 4) (2 3))"
  (->> (c/cartesian-product (range 1 7)
                            (range 1 7))
       (map #(vector (first %) (second %) (apply min-max-c %)))
       (filter #(some #{(- 9 (+ (first %) (second %)))} (last %)))
       (map #(vector (first %) (second %)))
       (map sort)
       (distinct)))

(defn digit-range
  "Given digit count return range of possible numbers."
  [n]
  (let [start-num (read-string
                   (apply str (take n (iterate identity "1"))))]
    (take-while #(<= (count (str %)) n)
                (iterate inc start-num))))

(def possible-a-b
  "Possible a/b pairs."
  (apply concat
         (map #(c/cartesian-product (digit-range (first %))
                                    (digit-range (second %)))
              possible-a-b-digit-counts)))

(defn pandigital-product?
  "Returns product when true."
  [a b]
  (let [c (* a b)]
    (when (and (= (count (digits (str a b c)))
                  (count (into #{} (digits (str a b c)))))
               (= (into #{} (digits (str a b c)))
                  #{1 2 3 4 5 6 7 8 9}))
      c)))

(defn solve []
  (->> (for [[a b] possible-a-b
             :when (pandigital-product? a b)]
         (* a b))
       (into #{})
       (reduce +)
       (time)))
