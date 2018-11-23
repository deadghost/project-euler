(ns project-euler.ex-026)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; A unit fraction contains 1 in the numerator. The decimal representation of
;; the unit fractions with denominators 2 to 10 are given:
;;
;; 1/2	= 	0.5
;; 1/3	= 	0.(3)
;; 1/4	= 	0.25
;; 1/5	= 	0.2
;; 1/6	= 	0.1(6)
;; 1/7	= 	0.(142857)
;; 1/8	= 	0.125
;; 1/9	= 	0.(1)
;; 1/10	= 	0.1 
;;
;; Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. It can be
;; seen that 1/7 has a 6-digit recurring cycle.
;;
;; Find the value of d < 1000 for which 1/d contains the longest recurring cycle
;; in its decimal fraction part.
;;==============================================================================
;; Approach
;;==============================================================================
;; We will perform step by step long division and store remainders. If a
;; duplicate remainder occurs then we know the decimal is recurrent 
;;==============================================================================
;; [Possible] Optimizations
;;==============================================================================
;; We do not check even integers because they will always be divisible without
;; recurrance.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn recurring-digit-count
  "Returns recurring digit count for the decimal portion of 1/d."
  ([d] (recurring-digit-count [] 1 d))
  ([remainders numerator d]
   (let [remainder (mod numerator d)]
     (cond (zero? remainder)
           0
           (contains? (into #{} remainders) remainder)
           (count (drop-while #(not (= % remainder)) remainders))
           :else
           (recur (conj remainders remainder)
                  (* remainder 10)
                  d)))))

(defn solve []
  (first (reduce (fn [[d-0 cycle-count-0] [d-1 cycle-count-1]]
                   (if (> cycle-count-0 cycle-count-1)
                     [d-0 cycle-count-0]
                     [d-1 cycle-count-1]))
                 (map #(vector % (recurring-digit-count %))
                      (take 999 (iterate inc 1))))))
