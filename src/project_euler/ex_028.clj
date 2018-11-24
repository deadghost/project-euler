(ns project-euler.ex-028)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Starting with the number 1 and moving to the right in a clockwise direction
;; a 5 by 5 spiral is formed as follows:
;; 
;; 21 22 23 24 25
;; 20  7  8  9 10
;; 19  6  1  2 11
;; 18  5  4  3 12
;; 17 16 15 14 13
;; 
;; It can be verified that the sum of the numbers on the diagonals is 101.
;; 
;; What is the sum of the numbers on the diagonals in a 1001 by 1001 spiral
;; formed in the same way?
;;==============================================================================
;; Approach
;;==============================================================================
;; The distance between corner numbers is 2*number-of-spiral(zero-indexed).
;; The corner of a new spiral can be calculated with the last corner number of
;; the previous spiral.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def last-number
  "The last and biggest number in the spiral grid."
  (* 1001 1001))

(def spiral-numbers
  "Lazy memoized infinite lazy seq of spiral numbers."
  (lazy-cat
   [(list 1)]
   (map (fn [previous-spiral-numbers]
          (let [corner-difference (* (count spiral-numbers) 2)
                previous-spiral-max (+ (last previous-spiral-numbers))]
            (take 4 (iterate #(+ corner-difference %)
                             (+ previous-spiral-max corner-difference)))))
        spiral-numbers)))

(defn solve []
  (->> (take-while #(<= (last %) last-number) spiral-numbers)
       (flatten)
       (apply +)))
