(ns clojure.core.matrix.stats
  (:use clojure.core.matrix))

(defn mean
  "Calculates the mean of a collection of values. Values may be scalars, vectors or higher-dimensional matrices."
  ([values]
    (let [n (count values)
          s (reduce add values)]
      (mul s (/ 1.0 n)))))