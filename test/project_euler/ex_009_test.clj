(ns project-euler.ex-009-test
  (:require [project-euler.ex-009 :as sut]
            [clojure.test :as t :refer [deftest is testing]]))

(deftest triplet-sum?
  (testing "Example triplet."
    (is (sut/triplet-sum? 3 4 12))))

(deftest solution
  (testing "Solution."
    (is (= (sut/solve) 31875000))))
