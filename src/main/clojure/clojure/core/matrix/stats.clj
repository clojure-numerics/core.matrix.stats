(ns clojure.core.matrix.stats
  (:use clojure.core.matrix))

(defn sum 
  "Calculates the sum of a collection of values. 
   Values may be scalars, vectors or higher-dimensional matrices."
  ([values]
    (if (== 1 (dimensionality values))
      (esum values)
      (let [values (slices values)
            result (mutable-matrix (first values))]
        (doseq [v (next values)]
          (add! result v))
        result))))

(defn sum-of-squares
  "Calculates the sum of squares of a collection of values. 
   Values may be scalars, vectors or higher-dimensional matrices."
  ([values]
    (if (== 1 (dimensionality values))
      (inner-product values values)
      (let [values (slices values)
            result (mutable-matrix (first values))]
        (doseq [v (next values)]
          (add-product! result v v))
        result))))

(defn mean
  "Calculates the mean of a collection of values. 
   Values may be scalars, vectors or higher-dimensional matrices."
  ([values]
    (let [values (slices values)
          n (count values)
          s (sum values)]
      (if (number? s)
        (/ s n)
        (scale! s (/ 1.0 n)) ;; abuse the fact that s must be a new mutable matrix....
        ))))

(defn variance
   "Calculates the unbiased sample variance of a set of values.
   Values may be scalars, vectors or higher-dimensional matrices."
   ([values]
     (let [n (count values)
           u (mean values)
           ss (sum-of-squares values)
           nuu (mul n (emul u u))]
       (if (number? ss)
         (* (- ss nuu) (/ 1.0 (dec n)))
         (do ;; must be a new mutable matrix, so we abuse this fact to use it as an accumulator...
           (sub! ss nuu) 
           (scale! ss (/ 1.0 (dec n)))
           ss)))))

(defn sd
  "Calculates the sample standard deviation of a set of values.
   Values may be scalars, vectors or higher-dimensional matrices."
  ([values]
    (sqrt (variance values))))

