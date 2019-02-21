(ns project-euler.ex-009
  (:require [clojure.math.combinatorics :as combo]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Special Pythagorean triplet
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; A Pythagorean triplet is a set of three natural numbers, a < b < c, for
;; which, a^2 + b^2 = c^2
;; For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.
;; There exists exactly one Pythagorean triplet for which a + b + c = 1000.
;; Find the product abc.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Solution 1: Bruteforce
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; The requirements can be written as a^2 + b^2 = (1000 - a - b)^2.
;;
;; Time complexity: O(n^2)
;;   a and b are both ~n and the combinations are n^2.
;; Space complexity: O(n^2)
;;   We expand the combinations before checking for the solution. This can be
;;   Reduced to O(n) or even O(1).
;; 
;; Improvements
;;==============================================================================
;; The number of a, b, and c combinations can be reduced to only combinations
;; that add up to 1000.
;; This problem can be broken down into subproblems.
;; a + b + c = 1000 is a three sum problem.
;; This can be reduced to a two sum problem.
;; a + b = d where d is 1000 - c.
;; The two sum problem has a time complexity of O(n) when a hashtable is used.
;; The three sum problem introduces c which increases time complexity to O(n^2).
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn triplet-sum?
  "a^2 + b^2 = (triplet-sum - a - b)^2
   Returns [a b c] when a + b + c = triplet-sum, otherwise returns nil."
  [a b triplet-sum]
  (when (= (+ (* a a) (* b b))
           (* (- triplet-sum a b) (- triplet-sum a b)))
    [a b (- triplet-sum a b)]))

(defn find-a-b-c
  "Returns the [a b c] values that solve the exercise."
  []
  (some #(apply triplet-sum? (concat % [1000]))
        (combo/combinations (take 1000 (iterate inc 1)) 2)))

(defn solve []
  (apply * (find-a-b-c)))
