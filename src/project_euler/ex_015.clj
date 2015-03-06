(ns project-euler.ex-015
  (:require [clojure.math.numeric-tower :as math]))

(defn next-row
  "Excludes the 1s on the edge of triangle."
  [row]
  (if (nil? (second row)) nil
      (cons (+ (first row) (second row))
            (next-row (rest row)))))

(defn pascal-t-next-row
  "Includes 1s on edge of triangle.
   Ugly/weird because no internet for docs."
  [row]
  (conj (into [] (cons 1 (next-row row))) 1))

(def pascal-triangle
  "Lazy rows of pascal's triangle."
  (lazy-cat [[1]] (map pascal-t-next-row pascal-triangle)))

(defn solve []
  (apply max (last (take 41 pascal-triangle))))

;; (solve) => 137846528820
