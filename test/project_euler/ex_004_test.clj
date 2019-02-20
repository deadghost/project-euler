(ns project-euler.ex-004-test
  (:require [project-euler.ex-004 :as sut]
            [clojure.test :as t :refer [deftest is testing]]))

(deftest largest-palindrome
  (testing "Solution."
    (is (= (sut/largest-palindrome)
           (sut/solution-2)
           906609))))
