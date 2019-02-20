(ns project-euler.ex-005
  (:require [clojure.math.numeric-tower :as math]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Smallest multiple
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 2520 is the smallest number that can be divided by each of the numbers from 1
;; to 10 without any remainder.
;; What is the smallest positive number that is evenly divisible by all of the
;; numbers from 1 to 20?
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; This problem is actually pretty silly and can be done by hand. We prime
;; factorize the numbers from 1 to 20 and merge these numbers together. The
;; highest frequency of a factor for a number in the set is used in the
;; superset. For example 16 is 2^4. This shadows 2^3, 2^2, and 2^1 in the final
;; superset.

(defn solve []
  (* 1 2 3 2 5 7 2 3 11 13 2 17 19))
