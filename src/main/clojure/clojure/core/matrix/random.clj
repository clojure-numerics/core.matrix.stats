(ns clojure.core.matrix.random
  (:use clojure.core.matrix))

(defn sample-uniform [size]
  (let [size (if (number? size) [size] size)]
    (compute-matrix 
      size
      (fn [& ixs]
        (Math/random)))))

(defn sample-normal [size]
  (let [size (if (number? size) [size] size)
        r (java.util.Random.)]
    (compute-matrix 
      size
      (fn [& ixs]
        (.nextGaussian r)))))

(defn sample-rand-int [size n]
  (let [size (if (number? size) [size] size)
        r (java.util.Random.)]
    (compute-matrix 
      size
      (fn [& ixs]
        (rand-int n)))))

;; TODO: should use normal approximation to binomial fdor large n
(defn sample-binomial 
  ([size p]
    (sample-binomial size p 1))
  ([size p n]
    (let [size (if (number? size) [size] size)
          r (java.util.Random.)
          n (long n)
          p (double p)]
      (compute-matrix 
        size
        (fn [& ixs]
          (loop [i 0 , res 0]
            (if (< i n)
              (recur (inc i) (if (<= (Math/random) p) (inc res) res))
              res)))))))