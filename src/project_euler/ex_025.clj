(ns project-euler.ex-025
  (:require
   [clojure.math.numeric-tower :as math]
   [project-euler.util :refer [take-until]]
   [project-euler.ex-002 :refer [fib-seq]]))

;; What is the first term in the Fibonacci sequence to contain 1000 digits?

(def first-1000-digit-fib-term
  (count (take-until #(= 1000 (count (str %))) fib-seq)))
