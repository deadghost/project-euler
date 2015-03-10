(ns project-euler.ex-017
  (:require
   [clojure.math.numeric-tower :as math]
   [project-euler.ex-005 :refer [primes]]
   [project-euler.ex-003 :refer [is-prime?]]))

(def singles
  ["one" "two" "three" "four" "five" "six" "seven" "eight" "nine"])

(def tens
  ["ten" "twenty" "thirty" "forty" "fifty" "sixty" "seventy" "eighty" "ninety"])

(def xteens
  ["eleven" "twelve" "thirteen" "fourteen" "fifteen"
   "sixteen" "seventeen" "eighteen" "nineteen"])

(def hundreds
  (map #(str % "hundred") singles))

(def one-to-nineteen
  (concat singles (cons "ten" xteens)))

(defn x0-x9 [ten]
  (cons ten (map #(str ten %) singles)))

(def one-to-ninety-nine
  (concat one-to-nineteen (flatten (map x0-x9 (drop 1 tens)))))

(defn x00-x99 [hundred]
  (cons hundred (map #(str hundred "and" %) one-to-ninety-nine)))

(def one-to-one-thousand
  (concat one-to-ninety-nine
          (flatten (map x00-x99 hundreds))
          ["onethousand"]))

(defn solve []
  (count (seq (apply str one-to-one-thousand))))
