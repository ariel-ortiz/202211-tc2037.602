;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Ejemplos de funciones de orden mayor en Clojure

(ns ejemplo-orden-mayor)

(defn compuesta
  [f g]
  (fn [x] (f (g x))))

(defn f1 [x] (* x x x))

(defn f2 [x] (+ x 2))

(def f3 (compuesta f1 f2))

(def f4 (compuesta f2 f1))

(def f5 (compuesta f3 f4))

(f1 2)
(f2 2)
(f3 2)
(f4 2)
(f5 2)
