(ns clojure.core.matrix.stats
  (:use clojure.core.matrix))

(defn sum 
  "Calculates the sum of a collection of values. Values may be scalars, vectors or higher-dimensional matrices."
  ([values]
    (reduce add values)))

(defn mean
  "Calculates the mean of a collection of values. Values may be scalars, vectors or higher-dimensional matrices."
  ([values]
    (let [n (count values)
          s (sum values)]
      (mul s (/ 1.0 n)))))

