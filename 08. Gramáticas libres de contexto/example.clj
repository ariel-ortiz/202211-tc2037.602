;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Ejemplo de CFG usando instaparse

(ns example
  (:require [instaparse.core :refer [parser]]))

(def example (parser "

  A =   'a' A 'b'
      | epsilon

"))

(example "baaabbba")
