(ns project-euler.ex-036
  (:require [project-euler.util :refer [palindrome?]]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Double-base palindromes
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; The decimal number, 585 = 1001001001 (binary), is palindromic in both bases.
;; 
;; Find the sum of all numbers, less than one million, which are palindromic in
;; base 10 and base 2.
;; 
;; (Please note that the palindromic number, in either base, may not include
;; leading zeros.)
;;==============================================================================
;; Approach
;;==============================================================================
;; We find all base 10 palindromes under one million. Then we test if their
;; binary forms are palindromes.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def base-ten-palindromes
  (filter palindrome? (take 999999 (iterate inc 1))))

(defn ten->base
  "Converts base 10 n integer to given base in a string."
  [n base]
  (Integer/toString n base))

(defn base-2 [n]
  (ten->base n 2))

(defn base-two-palindrome? [n]
  (palindrome? (base-2 n)))

(defn solve []
  (->> (filter base-two-palindrome? base-ten-palindromes)
       (apply +)
       (time)))
