(ns project-euler.ex-039
  (:require
   [clojure.algo.generic.functor :refer [fmap]]
   [clojure.math.combinatorics :refer [cartesian-product]]
   [clojure.math.numeric-tower :refer [sqrt]]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Integer right triangles
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; If p is the perimeter of a right angle triangle with integral length sides,
;; {a,b,c}, there are exactly three solutions for p = 120.
;; 
;; {20,48,52}, {24,45,51}, {30,40,50}
;; 
;; For which value of p ≤ 1000, is the number of solutions maximised?
;;==============================================================================
;; Approach
;;==============================================================================
;; a^2 + b^2 = c^2 Pythagorean Theorem.
;; a + b + c = p
;; There are < 1000 possible `a` values and < 1000 possible `b` values.
;; This is < 1M combinations. It's reasonable for us to try every a and b
;; combination.
;; We apply a^2 + b^2 for each combination and store the result.
;; We count the results and starting from the highest count, we look for the
;; c^2 where c is an integer.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def possible-a-b
  "a and b must be less than 500 because c > a and c > b."
  (take-while #(< % 500) (iterate inc 1)))

(def possible-c
  "c must be greater than a or b."
  (drop 2 (take 1000 (iterate inc 1))))

(defn c-sq
  "Calculates c^2 given a and b."
  [a b]
  (+ (* a a) (* b b)))

(defn <=1000?
  "We need to make sure a + b + c <= 1000."
  [a b c]
  (<= (+ a b c) 1000))

(defn int-rt-tri?
  "Returns c when c is an integer."
  [a b]
  (let [c (sqrt (c-sq a b))]
    (when (int? c) c)))

(defn int-rt-tri<=1000?
  "Returns the set of a, b, c, p when they are the lengths of the sides of an
  integer right triangle where the perimeter is less than or equal to 1000."
  [a b]
  (when-let [c (int-rt-tri? a b)]
    (when (<=1000? a b c)
      #{a b c (+ a b c)})))

(defn solve []
  ;; Pythagorean triples have the property of a < b < c meaning a cannot be
  ;; equal to b.
  (->> (cartesian-product possible-a-b possible-a-b)
       (map set) ; Removing dups
       (set)     ; Removing dups
       (filter #(= (count %) 2)) ; a < b < c
       (map #(apply int-rt-tri<=1000? %))
       (remove nil?)
       (group-by #(apply max %)) ; Group by perimeter
       (fmap count)
       (apply max-key val)
       (key)
       (time)))

;; "Elapsed time: 1248.459398 msecs"
