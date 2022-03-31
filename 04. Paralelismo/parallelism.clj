; Problem Set #4

(ns parallelism)

;----------------------------------------------------------
; Problem 1

(defn bits
  [x]
  (.bitCount (biginteger x)))

(defn fact-seq
  [n]
  (bits
    (loop [i 1
           r 1]
      (if (> i n)
        r
        (recur (inc i)
               (*' i r))))))

(fact-seq 1000)
