(ns project-euler.ex-007-test
  (:require [project-euler.ex-007 :as sut]
            [clojure.test :as t :refer [deftest is testing]]))

(deftest solution
  (testing "Solution."
    (is (= sut/prime-10001 104743))))
