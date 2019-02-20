(ns project-euler.ex-002-test
  (:require [project-euler.ex-002 :as sut]
            [clojure.test :as t :refer [deftest is testing]]))

(deftest solution-1
  (testing "fib-seq"
    (is (= (take 11 sut/fib-seq) [1 1 2 3 5 8 13 21 34 55 89])))
  (testing "Solution 1"
    (is (= (sut/solution-1) 4613732))))
