(ns clojure.core.matrix.stats
  (:use clojure.core.matrix))

(defn sum 
  "Calculates the sum of a collection of values. 
   Values may be scalars, vectors or higher-dimensional matrices."
  ([values]
    (reduce add values)))

(defn sum-of-squares
  "Calculates the sum of squares of a collection of values. 
   Values may be scalars, vectors or higher-dimensional matrices."
  ([values]
    (reduce add (map #(e* % %) values))))

(defn mean
  "Calculates the mean of a collection of values. 
   Values may be scalars, vectors or higher-dimensional matrices."
  ([values]
    (let [n (count values)
          s (sum values)]
      (scale s (/ 1.0 n)))))

(defn variance
   "Calculates the unbiased sample variance of a set of values.
   Values may be scalars, vectors or higher-dimensional matrices."
   ([values]
     (let [n (count values)
           u (mean values)
           ss (sum-of-squares values)]
       (mul (/ 1.0 (dec n)) (sub ss (mul n (emul u u)))))))

(defn sd
  "Calculates the sample standard deviation of a set of values.
   Values may be scalars, vectors or higher-dimensional matrices."
  ([values]
    (sqrt (variance values))))

