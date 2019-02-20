(ns project-euler.ex-003-test
  (:require [project-euler.ex-003 :as sut]
            [clojure.test :as t :refer [deftest is testing]]))

(deftest largest-prime-factor
  (testing "Solution."
    (is (= (sut/largest-prime-factor 600851475143)
           6857)))
  (testing "Other tests."
    (is (= (sut/largest-prime-factor 15)
           5))
    (is (= (sut/largest-prime-factor 49)
           7))))
