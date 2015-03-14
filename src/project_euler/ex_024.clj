(ns project-euler.ex-024
  (:require
   [clojure.math.numeric-tower :as math]
   [clojure.string :as str]))

(defn max-combo?
  "Is the permutation of coll the max?"
  [coll]
  (= (reverse (sort coll))
     coll))

(defn next-largest
  "Returns the smallest element in coll larger than n."
  [n coll]
  (first (drop-while #(<= (int %) (int n))
                     (sort coll))))

(defn next-permutation [last-perm last-n]
  (if (max-combo? (take-last last-n last-perm))
    (next-permutation last-perm (inc last-n))
    (let [last-n-seq (take-last last-n last-perm)
          next-largest-num (next-largest (first last-n-seq) last-n-seq)]
      (concat (take (- (count last-perm) last-n) last-perm)
              (cons next-largest-num
                    (sort (remove #{next-largest-num} last-n-seq)))))))

(def permutations
  (lazy-cat ["0123456789"]
            (map #(apply str (next-permutation % 2))
                 permutations)))

(defn solve []
  (last (take 1000000 permutations)))
