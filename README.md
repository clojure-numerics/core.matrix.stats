core.matrix.stats
=================

Statistical function library for Clojure using `core.matrix`

The purpose of this library is to provide useful statistical functions 
which are not part of core.matrix itself but are generally useful in statistical computing.

All of the functions are implemented on top of the core.matrix API: this means that you 
can safely use them with any core.matrix implementation, and you will gain the performance 
benefits of fast core.matrix implementations

`core.matrix.stats` is intended to be complementary with Incanter, and adopts the same 
conventions wherever possible.

### Examples

```clojure
;; Calculate the mean of four vectors

(mean [[1 2 3] [4 5 6] [7 8 9] [10 11 12]])
=> [5.5 6.5 7.5]

;; calculate the sample variance of a set of numbers
(variance [1 2 3 4 5])
=> 2.5
```