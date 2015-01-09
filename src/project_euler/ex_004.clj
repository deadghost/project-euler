(ns project-euler.ex-004
  (:require
   [clojure.math.numeric-tower :as math]
   [clojure.string :as str]))

;; A palindromic number reads the same both ways. The largest palindrome made
;; from the product of two 2-digit numbers is 9009 = 91 × 99.
;; Find the largest palindrome made from the product of two 3-digit numbers.

(def potential-palindromes
  (map #(Integer. (str % (str/reverse (str %)))) (take 900 (iterate dec 999))))

(defn three?
  "Is n a product of two 3 digit numbers?"
  [n]
  (some #(and (zero? (mod n %))
              (= (count (str (/ n %))) 3))
        (take 900 (iterate inc 100))))

(def largest-palindrome
  (first (filter three? potential-palindromes)))
