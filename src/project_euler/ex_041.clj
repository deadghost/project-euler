(ns project-euler.ex-041
  (:require [project-euler.util :refer [primes pandigital?]]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Pandigital prime
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; We shall say that an n-digit number is pandigital if it makes use of all the
;; digits 1 to n exactly once. For example, 2143 is a 4-digit pandigital and is
;; also prime.
;; 
;; What is the largest n-digit pandigital prime that exists?
;;==============================================================================
;; Approach
;;==============================================================================
;; The largest possible pandigital prime is 9 digits. 10 digits if we count 0.
;; We generate the primes and work backwards to check for pandigitality.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn solve []
  (->> (take-while #(<= % 987654321) primes)
       (reverse)
       (filter pandigital?)
       (first)
       (time)))

;; "Elapsed time: 74057.432023 msecs"
;; => 7652413
