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
(run-tests)
