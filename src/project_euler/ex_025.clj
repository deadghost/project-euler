(ns project-euler.ex-025
  (:require
   [clojure.math.numeric-tower :as math]
   [project-euler.ex-002 :refer [fib-seq]]))

;; What is the first term in the Fibonacci sequence to contain 1000 digits?

;; Might become part of clojure.core
;; Taken from http://dev.clojure.org/jira/browse/CLJ-1451
(defn take-until
  "Returns a lazy sequence of successive items from coll until
  (pred item) returns true, including that item. pred must be
  free of side-effects."
  [pred coll]
  (lazy-seq
    (when-let [s (seq coll)]
      (if (pred (first s))
        (cons (first s) nil)
        (cons (first s) (take-until pred (rest s)))))))

(def first-1000-digit-fib-term
  (count (take-until #(= 1000 (count (str %))) fib-seq)))
