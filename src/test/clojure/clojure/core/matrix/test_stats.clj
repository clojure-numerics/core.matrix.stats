(ns clojure.core.matrix.test-stats
  (:use clojure.core.matrix)
  (:use clojure.core.matrix.stats)
  (:use clojure.test))

(deftest test-mean
  (is (equals [1 2] (mean [[0 0] [1 2] [2 4]])))
  (is (== 1.5 (mean [0 1 2 3]))))

(deftest test-sum
  (is (== 6 (first (slices [6]))))
  (is (nil? (next (slices [6]))))
  (is (== 6 (sum [6])))
  (is (== 6 (sum [1 2 3])))
  (let [ss (sum [[1 1] [1 1] [1 2]])]
    (is (equals [3 4] ss))
    (is (mutable? ss)))) 

(deftest test-sum-of-squares
  (is (== 30 (sum-of-squares [1 2 3 4])))
  (let [ss (sum-of-squares [[0 1] [1 2] [2 3] [3 4]])]
    (is (equals [14 30] ss))
    (is (mutable? ss))))

(deftest test-variance
  (is (== 0.0 (variance [1 1 1])))
  (is (== 2.5 (variance [1 2 3 4 5]))))

(deftest test-sd
  (is (== 0.0 (sd [1 1 1]))))