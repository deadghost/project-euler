(ns project-euler.util)

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
