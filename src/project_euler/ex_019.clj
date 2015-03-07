(ns project-euler.ex-019)

(def month-set
  [31 28 31 30 31 30 31 31 30 31 30 31])

(def leap-month-set
  [31 29 31 30 31 30 31 31 30 31 30 31])

(defn leap-year? [year]
  (if (= (mod year 100) 0)
    (= (mod year 400) 0)
    (= (mod year 4) 0)))

(defn pick-month-set [year]
  (if (leap-year? year)
    leap-month-set
    month-set))

(defn count-mondays [day month-days count]
  (cond (empty? (rest month-days)) count
        (= (mod (+ day (first month-days)) 7) 0)
        (count-mondays 0 (rest month-days) (inc count))
        :else (count-mondays (mod (+ day (first month-days)) 7)
                             (rest month-days)
                             count)))

(defn solve
  "Exclude mondays in the year 1990."
  []
  (- (count-mondays 1
                    (flatten (map pick-month-set
                                  (take 101 (iterate inc 1900))))
                    0)
     (count-mondays 1 (pick-month-set 1990) 0)))
