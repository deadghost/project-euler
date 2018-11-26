(ns project-euler.ex-033
  (:require [project-euler.util :refer [digits remove-first]]
            [clojure.set :refer [intersection]]
            [clojure.data :refer [diff]]
            [clojure.math.numeric-tower :refer [abs]]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; The fraction 49/98 is a curious fraction, as an inexperienced mathematician
;; in attempting to simplify it may incorrectly believe that 49/98 = 4/8, which
;; is correct, is obtained by cancelling the 9s.
;; 
;; We shall consider fractions like, 30/50 = 3/5, to be trivial examples.
;; 
;; There are exactly four non-trivial examples of this type of fraction, less
;; than one in value, and containing two digits in the numerator and
;; denominator.
;; 
;; If the product of these four fractions is given in its lowest common terms,
;; find the value of the denominator.
;;==============================================================================
;; Approach
;;==============================================================================
;; We'll permutate brute force. Clojure already does fractions.
;; Note the solution asks for non-trivial examples meaning 0 cannot be the
;; cancelled digit.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def possible-nums
  "Possible numerators and denominators."
  (range 10 100))

(def possible-numerators
  "The numerator must be less than the denominator."
  (drop-last possible-nums))

(defn possible-denominators [numerator]
  (->> (for [d (digits numerator)]
         (filter #(some (fn [n] (= d n)) (digits %)) possible-nums))
       (apply concat)))

(defn cancelled-digit
  "We don't worry about the case where both digits can be cancelled because
  that would result in simplifying to 1."
  [numerator denominator]
  (first (intersection
          (set (digits numerator))
          (set (digits denominator)))))

(defn cancel-fraction
  [n d]
  (let [cancelled-digit (cancelled-digit n d)]
    [(first (remove-first #(= cancelled-digit %) (digits n)))
     (first (remove-first #(= cancelled-digit %) (digits d)))]))

(defn digit-cancelling-fraction? [n d]
  (let [cancelled-digit (cancelled-digit n d)
        [new-num new-den] (cancel-fraction n d)]
    (and (not (zero? cancelled-digit)) ; considered trivial
         (not (zero? new-den))
         (= (/ new-num new-den) (/ n d)))))

(defn solve []
  (->> (for [num possible-numerators]
         (for [den (filter #(> % num) (possible-denominators num))
               :when (digit-cancelling-fraction? num den)]
           [num den]))
       (remove empty?)
       (map first)
       (map #(/ (first %) (second %)))
       (reduce *)
       (denominator)))

