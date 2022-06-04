(ns grammar
  (:require [clojure.test :refer [deftest is run-tests]])
  (:require [instaparse.core :refer [parser]])
  (:import (instaparse.gll Failure)))

(defn fails? [r] (instance? Failure r))
(defn succeeds? [r] (not (fails? r)))

;----------------------------------------------------------
; Problem 1

(def start-and-end (parser "

  S =    '$'
       | '#'
       | '$' T '$'
       | '#' T '#'

  T =    T '$'
       | T '#'
       | Epsilon

"))

(deftest test-start-and-end
  (is (succeeds? (start-and-end "$")))
  (is (succeeds? (start-and-end "#")))
  (is (succeeds? (start-and-end "$$")))
  (is (succeeds? (start-and-end "##")))
  (is (succeeds? (start-and-end "$$$$$$$#$$#$$#$##$")))
  (is (succeeds? (start-and-end "#$$$#$$$$#$$$$#$####")))
  (is (fails? (start-and-end "")))
  (is (fails? (start-and-end "$#")))
  (is (fails? (start-and-end "#$")))
  (is (fails? (start-and-end "###$$#$#$$$#$$$####$")))
  (is (fails? (start-and-end "$#$#$#$$$$#$$$#$$$$#$$#")))
  (is (fails? (start-and-end "#######################$"))))

;----------------------------------------------------------
; Problem 2

(def palindrome (parser "

  S =   Epsilon
      | '0'
      | '1'
      | '0' S '0'
      | '1' S '1'

"))

(deftest test-palindrome
  (is (succeeds? (palindrome "")))
  (is (succeeds? (palindrome "0")))
  (is (succeeds? (palindrome "1")))
  (is (succeeds? (palindrome "11")))
  (is (succeeds? (palindrome "00")))
  (is (succeeds? (palindrome "010")))
  (is (succeeds? (palindrome "1111111")))
  (is (succeeds? (palindrome "000010000")))
  (is (succeeds? (palindrome "01001110101110010")))
  (is (fails? (palindrome "01")))
  (is (fails? (palindrome "10")))
  (is (fails? (palindrome "1010")))
  (is (fails? (palindrome "10000000")))
  (is (fails? (palindrome "00010001")))
  (is (fails? (palindrome "1010011010")))
  (is (fails? (palindrome "111111111111111111110"))))

;----------------------------------------------------------
; Problem 3

(def balanced-parentheses (parser "

   P =   Epsilon
         | '(' P ')' P

"))

(deftest test-balanced-parentheses
  (is (succeeds? (balanced-parentheses "")))
  (is (succeeds? (balanced-parentheses "()")))
  (is (succeeds? (balanced-parentheses "((((()))))")))
  (is (succeeds? (balanced-parentheses "()()()()()")))
  (is (succeeds? (balanced-parentheses
                   "(()()())((())(()()()))(((())))")))
  (is (fails? (balanced-parentheses "(")))
  (is (fails? (balanced-parentheses ")")))
  (is (fails? (balanced-parentheses "((((())))")))
  (is (fails? (balanced-parentheses "))))((((")))
  (is (fails? (balanced-parentheses
                "(()()())((())(()()()))((((())))"))))

;----------------------------------------------------------
; Problem 4

(def o-in-the-middle (parser "

    S =   'o'
        | T S T

    T = 'x' | 'o'

"))

(deftest test-o-in-the-middle
  (is (succeeds? (o-in-the-middle "o")))
  (is (succeeds? (o-in-the-middle "xox")))
  (is (succeeds? (o-in-the-middle "oooooxxxx")))
  (is (succeeds? (o-in-the-middle "oxxoooxooxoxoxx")))
  (is (succeeds? (o-in-the-middle "ooooooooooooooo")))
  (is (succeeds? (o-in-the-middle "xxxxxxxxxxoxxxxxxxxxx")))
  (is (fails? (o-in-the-middle "")))
  (is (fails? (o-in-the-middle "ox")))
  (is (fails? (o-in-the-middle "oxo")))
  (is (fails? (o-in-the-middle "oxxoooxxoxoxoxx")))
  (is (fails? (o-in-the-middle "xxxxxxxxxxxxxxxxxxxxx")))
  (is (fails? (o-in-the-middle "oooooooooooooooooooooo"))))

(run-tests)
