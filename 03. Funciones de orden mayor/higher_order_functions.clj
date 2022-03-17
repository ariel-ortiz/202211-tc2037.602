; Problem Set #4

(ns higher-order-functions
  (:require [clojure.test :refer [deftest is run-tests]])
  (:require [clojure.algo.generic.math-functions
             :refer [abs approx=]]))

;----------------------------------------------------------
; Problem 1
(defn argswap
  [f]
  (fn [x y] (f y x)))

(deftest test-argswap
  (is (= '(2 1)
         ((argswap list) 1 2)))
  (is (= -7
         ((argswap -) 10 3)))
  (is (= 1/4
         ((argswap /) 8 2)))
  (is (= '((4 5 6) 1 2 3)
         ((argswap cons) '(1 2 3) '(4 5 6))))
  (is (= '(1 0 4 25 100)
         ((argswap map) '(-1 0 2 5 10) #(* % %)))))

;----------------------------------------------------------
; Problem 3
(defn linear-search
  ([vct x] (linear-search vct x =))
  ([vct x eq-fun?]
   (let [n (count vct)]
     (loop [index 0]
       (cond
         (= index n)
         nil

         (eq-fun? (vct index) x)
         index

         :else
         (recur (inc index)))))))

(linear-search [5 1 4 2 1] 3)

(deftest test-linear-search
  (is (nil? (linear-search [] 5 =)))
  (is (= 0 (linear-search [5] 5 =)))
  (is (= 4 (linear-search
             [48 77 30 31 5 20 91 92
              69 97 28 32 17 18 96]
             5
             =)))
  (is (= 3 (linear-search
             ["red" "blue" "green" "black" "white"]
             "black"
             identical?)))
  (is (nil? (linear-search
              [48 77 30 31 5 20 91 92
               69 97 28 32 17 18 96]
              96.0
              =)))
  (is (= 14 (linear-search
              [48 77 30 31 5 20 91 92
               69 97 28 32 17 18 96]
              96.0
              ==)))
  (is (= 8 (linear-search
             [48 77 30 31 5 20 91 92
              69 97 28 32 17 18 96]
             70
             #(<= (abs (- %1 %2)) 1)))))

;----------------------------------------------------------
; Problem 5
(declare deriv) ;;; Borrar cuando hayan impelementado deriv

(defn newton
  [f n]
  (if (zero? n)
    0
    (let [x-n-1 (newton f (dec n))
          f' (deriv f 0.0001)]
      (- x-n-1 (/ (f x-n-1)
                  (f' x-n-1))))))

;----------------------------------------------------------
(run-tests)
