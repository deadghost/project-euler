(ns project-euler.ex-005-test
  (:require [project-euler.ex-005 :as sut]
            [clojure.test :as t :refer [deftest is testing]]))

(deftest Solution
  (testing "Solution."
    (is (= (sut/solve) 232792560))))
