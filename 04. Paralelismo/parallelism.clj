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

(defn fact-range
  [[start end]]
  (loop [i start
         r 1]
    (if (= i end)
      r
      (recur (inc i)
             (*' i r)))))

(defn compute-ranges
  [n p]
  (partition 2
             1
             (concat (range 1 n (quot n p))
                     [(inc n)])))

(defn fact-par
  [n]
  (->>
    (.availableProcessors (Runtime/getRuntime))
    (compute-ranges n)
    (pmap fact-range)
    (reduce *')
    (bits)))

(def n 200000)
(time (fact-seq n))
(time (fact-par n))

;;; T1 = 13534.6525
;;; Tp = 632.058
;;; p = 8
;;; Sp = 13534.6525 / 632.058 = 21.413624224359157
