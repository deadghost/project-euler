(ns project-euler.ex-016
  (:require
   [clojure.math.numeric-tower :as math]
   [project-euler.ex-008 :refer [char-to-int]]))

;; 215 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
;; What is the sum of the digits of the number 2^1000?

(apply + (map char-to-int (str (math/expt 2 1000))))
