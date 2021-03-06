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

(defn f [x y z w]
  (+ (* x y)
     (- z w)))

(defn f' [x]
  (fn [y]
    (fn [z]
      (fn [w]
        (+ (* x y)
          (- z w))))))

(f 1 2 3 4)

((((f' 1) 2) 3) 4)

(defn my-map
  ([fun s]
   (if (empty? s)
     ()
     (cons (fun (first s))
           (my-map fun (rest s)))))
  ([fun s & a]
   (let [args (cons s a)]
     (if (some empty? args)
       ()
       (cons (apply fun (my-map first args))
             (apply my-map fun (my-map rest args)))))))

(my-map * [0 1 2] [3 4 5] [6 7 8 9])

(my-map first [[0 1 2] [3 4 5] [6 7 8]])
