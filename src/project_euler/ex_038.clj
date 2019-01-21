(ns project-euler.ex-038
  (:require [project-euler.util :refer [digits]]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Pandigital multiples
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Take the number 192 and multiply it by each of 1, 2, and 3:
;; 
;; 192 × 1 = 192
;; 192 × 2 = 384
;; 192 × 3 = 576
;; 
;; By concatenating each product we get the 1 to 9 pandigital, 192384576.
;; We will call 192384576 the concatenated product of 192 and (1,2,3)
;; 
;; The same can be achieved by starting with 9 and multiplying by 1, 2, 3, 4,
;; and 5, giving the pandigital, 918273645, which is the concatenated product
;; of 9 and (1,2,3,4,5).
;; 
;; What is the largest 1 to 9 pandigital 9-digit number that can be formed as
;; the concatenated product of an integer with (1,2, ... , n) where n > 1?
;;==============================================================================
;; Approach
;;==============================================================================
;; The biggest integer possible is < (100000 / 2). From here we can attempt
;; to bruteforce a solution.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def biggest-possible-i
  (/ 100000 2))

(defn pandigital? [i]
  (when (and (not (contains? (into #{} (digits i)) 0))
             (= (count (into #{} (digits i)))
                9))
    i))

(defn pandigital-multiple? [i]
  (let [concat-val (->> (map (partial * i)
                             (iterate inc 1))
                        (reductions (partial str))
                        (take-while #(<= (count (digits %)) 9))
                        (#(when (> (count %) 1)
                            (last %))))]
    (pandigital? concat-val)))

(defn solve []
  (->> (take-while #(<= % biggest-possible-i) (iterate inc 1))
       (map pandigital-multiple?)
       (remove nil?)
       (map read-string)
       (apply max)
       (time)))