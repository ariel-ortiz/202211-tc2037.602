; Problem Set #5

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

;----------------------------------------------------------
; Problem 4

(defn create-random-data
  [n]
  (repeatedly n #(rand-int 1000)))

;(apply <= (sort (create-random-data 1000)))

(defn insertion-sort
  [s]
  (loop [s s
         r ()]
    (if (empty? s)
      r
      (let [x (first s)
            [before after] (split-with #(< % x) r)]
        (recur (rest s)
               (concat before [x] after))))))

(defn merge-algorithm
  [a b]
  (loop [a a
         b b
         r ()]
    (cond
      (empty? a)
      (concat (reverse r) b)

      (empty? b)
      (concat (reverse r) a)

      (< (first a) (first b))
      (recur (rest a)
             b
             (cons (first a) r))

      :else
      (recur a
             (rest b)
             (cons (first b) r)))))

(defn hybrid-sort-seq
  [s]
  (if (< (count s) 100)
    (insertion-sort s)
    (let [[a b] (split-at (quot (count s) 2) s)]
      (merge-algorithm
        (hybrid-sort-seq a)
        (hybrid-sort-seq b)))))

(defn hybrid-sort-par
  [s]
  (if (< (count s) 100)
    (insertion-sort s)
    (let [splitted (split-at (quot (count s) 2) s)]
      (apply merge-algorithm
             (pmap hybrid-sort-par splitted)))))

(def n 200000)
(def random-data (create-random-data n))
(apply <= random-data)
(apply <= (time (hybrid-sort-seq random-data)))
(apply <= (time (hybrid-sort-par random-data)))
(apply <= (time (sort random-data)))

;;; T1 = 1956.221042
;;; Tp = 648.264875
;;; p = 8
;;; Sp = 1956.221042 / 648.264875 = 3.0176261547411465
