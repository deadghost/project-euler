(ns project-euler.ex-006-test
  (:require [project-euler.ex-006 :as sut]
            [clojure.test :as t :refer [deftest is testing]]))

(deftest sum-sq-difference 
  (testing "Solution."
    (is (= (sut/sum-sq-difference) 25164150))))
