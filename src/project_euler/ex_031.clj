(ns project-euler.ex-031)

;; In England the currency is made up of pound, £, and pence, p, and there are
;; eight coins in general circulation:

;;     1p, 2p, 5p, 10p, 20p, 50p, £1 (100p) and £2 (200p).

;; It is possible to make £2 in the following way:

;;     1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p

;; How many different ways can £2 be made using any number of coins?

(def coins [200 100 50 20 10 5 2 1])

(defn number-of-ways
  "Find number of ways to make total amt using coin denominations in coins."
  [amt coins]
  (cond (empty? coins) 0 ; no coins, no ways
        (< amt 0) 0 ; amt cannot be made using the same coin
        (= amt 0) 1 ; amt can be made using the same coin
                 ;; ways to make amt without one coin
        :else (+ (number-of-ways amt (rest coins)) 
                 ;; ways to make amt when coin is used
                 (number-of-ways (- amt (first coins)) coins))))

(number-of-ways 200 coins)
