(ns project-euler.ex-049
  (:require [clojure.math.numeric-tower :as math]
            [clojure.math.combinatorics :as combo]
            [project-euler.ex-005 :refer [primes]]))

(def potential-primes
  "Primes between 1000 and 10000 exclusive."
  (->> (take-while #(< % 10000) primes)
       (drop-while #(< % 1000))))

(defn permutate
  "Returns permutations of n."
  [n]
  (map (comp int bigint #(apply str %))
       (combo/permutations (seq (str n)))))

(defn prime?
  "Just a quick prime check predicate that will only work in this ex."
  [n]
  (some #{n} potential-primes))

(defn solves-ex?
  "Returns vector of primes that solve the exercise as true value."
  [prime]
  (let [prime-permutations (filter prime? (into #{} (permutate prime)))]
    (when-let [difference (->> (sort > prime-permutations)
                               (#(combo/combinations % 2))
                               (map (comp math/abs (partial apply -)))
                               frequencies
                               (filter #(= 2 (val %)))
                               ffirst)]
      (when (and (some #{(+ prime difference)} prime-permutations)
                 (some #{(+ prime difference difference)} prime-permutations))
        [prime (+ prime difference) (+ prime difference difference)]))))

(defn solve []
  (->> (first (filter solves-ex? potential-primes))
       solves-ex?
       (apply str)))
