; Problem Set #2

(ns repetitions
  (:require [clojure.test :refer [deftest is run-tests]])
  (:require [clojure.algo.generic.math-functions
             :refer [sqr sqrt approx=]]))

;----------------------------------------------------------
; Problem 1

;; Recursive implementation
;(defn enlist
;  [s]
;  (if (empty? s)
;    ()
;    (cons (list (first s))
;          (enlist (rest s)))))

;; Loop/Recur implementation
;(defn enlist
;  [s]
;  (loop [remainder s
;         result []]
;    (if (empty? remainder)
;      result
;      (recur (rest remainder)
;             (conj result
;                   (list (first remainder)))))))

;; Clojure sequence API implementation
(defn enlist
  [s]
  (map list s))

(deftest test-enlist
  (is (= () (enlist ())))
  (is (= '((a) (b) (c)) (enlist '(a b c))))
  (is (= '((1) (2) (3) (4)) (enlist [1 2 3 4])))
  (is (= '(((1 2 3)) (4) ((5)) (7) (8))
         (enlist '((1 2 3) 4 (5) 7 8)))))

;----------------------------------------------------------
; Problem 2

(defn positives
  [s]
  (filter pos? s))

(deftest test-positives
  (is (= () (positives ())))
  (is (= () (positives [-4 -1 -10 -13 -5])))
  (is (= [3 6] (positives [-4 3 -1 -10 -13 6 -5])))
  (is (= [4 3 1 10 13 6 5] (positives [4 3 1 10 13 6 5]))))

;----------------------------------------------------------
; Problem 3

(defn add-squares
  [s]
  (reduce + (map sqr s)))

(deftest test-add-squares
  (is (= 0 (add-squares [])))
  (is (= 25 (add-squares [5])))
  (is (= 30 (add-squares [2 4 1 3])))
  (is (= 385 (add-squares [1 2 3 4 5 6 7 8 9 10]))))

;----------------------------------------------------------
; Problem 4

(defn dup
  [x]
  (list x x))

(dup 5)
(dup 123)

(defn duplicate
  [s]
  (mapcat dup s))

(deftest test-duplicate
  (is (= [1 1 2 2 3 3 4 4 5 5]
         (duplicate [1 2 3 4 5])))
  (is (= ()
         (duplicate ())))
  (is (= '(a a)
         (duplicate '(a))))
  (is (= '(a a b b c c d d e e f f g g h h)
         (duplicate '(a b c d e f g h)))))

;----------------------------------------------------------
; Problem 5

;; Recursive implementation
;(defn fib
;  [n]
;  (if (<= n 1)
;    n
;    (+' (fib (- n 1))
;        (fib (- n 2)))))

;; Loop/Recur implementation
;(defn fib
;  [n]
;  (loop [a 0
;         b 1
;         n n]
;    (if (zero? n)
;      a
;      (recur b
;             (+' a b)
;             (dec n)))))

(defn fib-aux
  [[a b]]
  [b (+ a b)])

(defn fib
  [n]
  (first (nth (iterate fib-aux
                       [0 1])
              n)))

(deftest test-fib
  (is (= 0
         (fib 0)))
  (is (= 1
         (fib 1)))
  (is (= 1
         (fib 2)))
  (is (= 5
         (fib 5)))
  (is (= [0 1 1 2 3 5 8 13 21 34 55 89 144 233 377 610
          987 1597 2584 4181 6765]
         (map fib (range 21))))
  (is (= 267914296
         (fib 42))))

;----------------------------------------------------------
(run-tests)
