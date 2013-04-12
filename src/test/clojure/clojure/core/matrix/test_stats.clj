(ns clojure.core.matrix.test-stats
  (:use clojure.core.matrix)
  (:use clojure.core.matrix.stats)
  (:use clojure.test))

(deftest test-mean
  (is (equals [1 2] (mean [[0 0] [1 2] [2 4]])))
  (is (equals 1.5 (mean [0 1 2 3]))))

(deftest test-sum
  (is (== 6 (sum [1 2 3 ])))
  (is (equals [3 4] (sum [[1 1] [1 1] [1 2]])))) 

(deftest test-sum-of-squares
  (is (== 30 (sum-of-squares [1 2 3 4]))))