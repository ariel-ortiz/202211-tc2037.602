;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Ejemplos de repeticiones en Clojure

(ns ejemplo-repeticiones)

;----------------------------------------------------------
; Versión 1: factorial usando recursión
(defn !-1
  [n]
  (if (zero? n)
    1
    (*' n (!-1 (dec n)))))

(!-1 0)
(!-1 5)
(!-1 1000)
;(!-1 5000) ; Stack overflow

;----------------------------------------------------------
; Versión 2: factorial usando loop/recur
(defn !-2
  [n]
  (loop [result 1
         i 1]
    (if (> i n)
      result
      (recur (*' result i)
             (inc i)))))

(!-2 0)
(!-2 5)
(!-2 1000)
(!-2 5000)
(!-2 10000)

;----------------------------------------------------------
; Versión 3: factorial usando API secuencias
(defn !-3
  [n]
  (reduce *' (range 1 (inc n))))

(!-3 0)
(!-3 5)
(!-3 1000)
(!-3 5000)
(!-3 10000)
