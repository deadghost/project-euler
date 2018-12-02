(ns project-euler.util
  (:require [project-euler.ex-005 :refer [is-divisible-by?
                                          numbers-under-sqrt]]))

(defn take-until
  "Returns a lazy sequence of successive items from coll until
  (pred item) returns true, including that item. pred must be
  free of side-effects."
  [pred coll]
  (lazy-seq
   (when-let [s (seq coll)]
     (if (pred (first s))
       (cons (first s) nil)
       (cons (first s) (take-until pred (rest s)))))))

(defn digits
  "Given an integer, returns a list of digits."
  [n]
  (->> n str (map (comp read-string str))))

(defn remove-first
  "Returns seq with the first element that fulfills the predicate removed."
  [pred s]
  (if (pred (first s))
    (rest s)
    (let [[a b] (split-with (comp not pred) s)]
      (if (empty? a)
        s
        (concat a (rest b))))))

(defn factorial [n]
  (reduce * (take n (iterate inc 1))))

(defn palindrome? [n]
  (= (reverse (digits n))
     (digits n)))

(def primes
  "Lazy memoized infinite lazy seq of prime numbers."
  (lazy-cat [2]
            (remove #(is-divisible-by? % (cons 2 (numbers-under-sqrt % primes))) 
                    (iterate inc (last primes)))))

(defn prime? [n]
  (= (first (drop-while #(< % n) primes))
     n))
