(ns project-euler.ex-008-test
  (:require [project-euler.ex-008 :as sut]
            [clojure.test :as t :refer [deftest is testing]]))

(deftest solution
  (testing "Solution."
    (is (= (sut/solve) 23514624000))))
