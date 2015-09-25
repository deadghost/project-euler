(ns project-euler.ex-048
  (:require [clojure.math.numeric-tower :as math]))

;; The series, 1^1 + 2^2 + 3^3 + ... + 10^10 = 10405071317.

;; Find the last ten digits of the series, 1^1 + 2^2 + 3^3 + ... + 1000^1000.

(defn solution []
  (->> (reduce +' (map #(math/expt % %)
                       (take 1000 (iterate inc 1))))
       str
       (take-last 10)
       (apply str)))
