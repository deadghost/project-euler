(ns project-euler.ex-034
  (:require [project-euler.util :refer [factorial digits]]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.
;; 
;; Find the sum of all numbers which are equal to the sum of the factorial of
;; their digits.
;; 
;; Note: as 1! = 1 and 2! = 2 are not sums they are not included.
;;==============================================================================
;; Approach
;;==============================================================================
;; We need to establish the minimum and maximum possible numbers. The minimum
;; is >= 10 because the number must be a sum of factorials. The maximum can
;; be estimated by finding the maximum number of digits a number can have and
;; fulfill those requirements.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def minimum-n 10)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; We'll find the maximum number of digits n can have by checking the possible
;; factorial sum digit ranges.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn factorial-sum [n]
  (reduce + (map factorial (digits n))))

(defn check-digit-range
  "Returns possible factorial sum digit ranges."
  [n-digits]
  [(->> (read-string (apply str (take n-digits (iterate identity 1))))
        (factorial-sum)
        (digits)
        (count))
   (->> (read-string (apply str (take n-digits (iterate identity 9))))
        (factorial-sum)
        (digits)
        (count))])

(defn in-range? [[min max] n]
  (and (>= n min)
       (<= n max)))

(def max-length
  "Maximum n length."
  (last (take-while #(in-range? (check-digit-range %) %) (iterate inc 1))))

(def maximum-n
  (read-string (apply str (take max-length (iterate inc 1)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn digit-factorial? [n]
  (= (factorial-sum n) n))

(defn solve []
  (->> (take-while #(<= % maximum-n) (iterate inc minimum-n))
       (filter digit-factorial?)
       (reduce +)))
