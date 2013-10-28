(ns clojure.core.matrix.test-random
  (:use clojure.core.matrix)
  (:use clojure.core.matrix.random)
  (:use clojure.test))

(deftest test-sample-uniform
  (is (= [10] (shape (sample-uniform 10))))
  (is (= [10] (shape (sample-uniform [10]))))
  ;; (is (number? (scalar (sample-uniform [])))) ;; TODO fix with latest core.matrix
  
  )
