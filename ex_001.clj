(ns project-euler.ex-001)

;; If we list all the natural numbers below 10 that are multiples of 3 or 5, we
;; get 3, 5, 6 and 9. The sum of these multiples is 23.

;; Find the sum of all the multiples of 3 or 5 below 1000.

;; more composable way
(def three-or-five
  (map #(if (or (zero? (mod % 3))
                (zero? (mod % 5)))
          %
          0)
          (iterate inc 1)))

(apply + (take 999 three-or-five))

;; shorter way
(apply + (filter #(or (zero? (mod % 3))
                      (zero? (mod % 5)))
                 (range 1 1000)))
