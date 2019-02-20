(ns project-euler.ex-004
  (:require
   [clojure.math.numeric-tower :as math]
   [clojure.string :as str]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Largest palindrome product
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; A palindromic number reads the same both ways. The largest palindrome made
;; from the product of two 2-digit numbers is 9009 = 91 × 99.
;; Find the largest palindrome made from the product of two 3-digit numbers.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; We can do this problem in two orders. We can generate the palindromes then
;; see if they are a product of three digit numbers or we can multiply the
;; three digit numbers then check if they're palindromes.

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Solution 1: Palindrome first
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def potential-palindromes
  (map #(Integer. (str % (str/reverse (str %)))) (take 900 (iterate dec 999))))

(defn three?
  "Is n a product of two 3 digit numbers?"
  [n]
  (some #(and (zero? (mod n %))
              (= (count (str (/ n %))) 3))
        (take 900 (iterate inc 100))))

(defn largest-palindrome []
  (first (filter three? potential-palindromes)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Solution 2: Multiply first
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn palindrome? [n]
  (= (str n) (str/reverse n)))

;; I'll leave this as an exercise for another time.
