(ns project-euler.ex-067
  (:require [clojure.string :as str]))

(def pyramid
  (->> (str/split (slurp "resources/p067_triangle.txt") #"\n")
       (map #(str/split % #" "))
       (map #(map (comp int bigint) %))
       (map #(into [] %))))

(defn max-path-row
  "Finds maximum path to each node given the previous max path row and next
  node row."
  [last-max-path-row row]
  (->> (map max (cons 0 last-max-path-row) (conj last-max-path-row 0))
       (map + row)
       (into [])))

(def pyramid-path-sum
  "I'm not actually sure how to store this in a function."
  (lazy-cat [(first pyramid)]
            (map max-path-row pyramid-path-sum (rest pyramid))))

(defn solve []
  (apply max (last pyramid-path-sum)))
