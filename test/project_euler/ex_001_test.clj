(ns project-euler.ex-001-test
  (:require [project-euler.ex-001 :as sut]
            [clojure.test :as t :refer [deftest is testing]]))

(deftest three-or-five
  (testing "Method 1"
    (is (= (take 5 sut/three-or-five)
           '(3 5 6 9 10)))))
