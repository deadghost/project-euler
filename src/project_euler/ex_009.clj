(ns project-euler.ex-009
  (:require
   [clojure.math.combinatorics :as combo]))

;; A Pythagorean triplet is a set of three natural numbers, a < b < c, for
;; which, a2 + b2 = c2
;; For example, 32 + 42 = 9 + 16 = 25 = 52.
;; There exists exactly one Pythagorean triplet for which a + b + c = 1000.
;; Find the product abc.

(defn solve-ex9?
  "a^2 + b^2 = (1000 - a - b)^2
   Returns [a b c] when values fulfill exercise requirements otherwise
   returns nil."
  [a b]
  (when (= (+ (* a a) (* b b))
         (* (- 1000 a b) (- 1000 a b)))
    [a b (- 1000 a b)]))

(defn find-a-b-c
  "Returns the [a b c] values that solve the exercise."
  []
  (some #(apply solve-ex9? %)
        (combo/combinations (take 1000 (iterate inc 1)) 2)))

(defn solve-ex9
  "Solve exercise."
  []
  (apply * (find-a-b-c)))
