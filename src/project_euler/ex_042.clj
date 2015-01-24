(ns project-euler.ex-042
  (:require
   [clojure.string :as str]
   [project-euler.util :refer [take-until]]
   [project-euler.ex-012 :refer [triangle-numbers]]))

(def word-list
  (-> (slurp "resources/p042_words.txt")
      (#(str/replace % #"[\",]+" " "))
      str/trim
      (#(str/split % #" +"))))

(defn char-to-int
  "Returns char position in alphabet. \A -> 1."
  [char]
  (- (int char) 64))

(defn word-sum
  "Given a string, convert characters into ints by alphabet position and sum."
  [word]
  (apply + (map char-to-int (seq word))))

(defn triangle-number?
  "Return n if n is a triangle number."
  [n]
  (when (some #(= n %) (take-until #(<= n %) triangle-numbers))
    n))

(->> (map word-sum word-list)
     (filter triangle-number?)
     count)
