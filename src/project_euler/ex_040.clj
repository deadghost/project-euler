(ns project-euler.ex-040
  (:require [project-euler.util :refer [char->int]]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Champernowne's constant
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; An irrational decimal fraction is created by concatenating the positive
;; integers:
;; 
;; 0.123456789101112131415161718192021...
;; 
;; It can be seen that the 12th digit of the fractional part is 1.
;; 
;; If dn represents the nth digit of the fractional part, find the value of
;; the following expression.
;; 
;; d1 × d10 × d100 × d1000 × d10000 × d100000 × d1000000
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn irrational [n]
  "The fractional portion of our irrational number to the nth decimal."
  (reduce (fn [i j]
            (let [k (str i j)]
              (if (>= (count k) n)
                (reduced k) ; short-circuit
                k)))
          (iterate inc 1)))

(defn select-index
  "Like select-keys but for indexes of sequences."
  [xs indexseq]
  (map #(get xs %) indexseq))

(defn solve []
  (->> (select-index (irrational 1000000) [0 9 99 999 9999 99999 999999])
       (map char->int)
       (reduce *)
       (time)))

;; "Elapsed time: 82737.127088 msecs"
;; This is weirdly slow. I didn't expect it to be efficient but I didn't expect
;; it to be this slow. Is counting on a string or accessing it by index really
;; slow?
