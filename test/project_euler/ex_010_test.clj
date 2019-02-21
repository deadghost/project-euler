(ns project-euler.ex-010-test
  (:require [project-euler.ex-010 :as sut]
            [clojure.test :as t :refer [deftest is testing]]))

(deftest solution
  (testing "Solution."
    (is (= (sut/solve) 142913828922))))
