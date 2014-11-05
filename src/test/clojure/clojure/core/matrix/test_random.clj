(ns clojure.core.matrix.test-random
  (:use clojure.core.matrix)
  (:use clojure.core.matrix.random)
  (:use clojure.test))

(deftest test-sample-uniform
  (is (= [10] (shape (sample-uniform 10))))
  (is (= [10] (shape (sample-uniform [10]))))
  ;; (is (number? (scalar (sample-uniform [])))) ;; TODO fix with latest core.matrix
  
  )

(deftest test-randoms
  (let [rs (randoms)]
    (is (= (take 100 rs) (take 100 rs)))
    (is (<= 0 (reduce + (take 1000 rs)) 1000)))
  (let [rs (nnext (randoms))]
    (is (= (take 100 rs) (take 100 rs)))
    (is (<= 0 (reduce + (take 1000 rs)) 1000)))) 
