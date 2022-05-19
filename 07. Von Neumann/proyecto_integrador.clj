(ns proyecto-integrador)

(defrecord Machine [memory pc sp])

(defn make-machine
  [code size]
  (->Machine (vec (take size (concat code
                                     (repeat 0))))
             0
             size))

(defn ct
  [{:keys [memory pc sp] :as machine}]
  (assoc machine
    :memory (assoc memory
              (dec sp)
              (memory (inc pc)))
    :pc (+ pc 2)
    :sp (dec sp)))

(defn out
  [{:keys [memory pc sp] :as machine}]
  (print (str (memory sp) " "))
  (assoc machine
    :pc (inc pc)
    :sp (inc sp)))

(defn make-operation
  [operation]
  (fn [{:keys [memory pc sp] :as machine}]
    (assoc machine
      :memory (assoc memory
                (inc sp)
                (operation (memory (inc sp))
                           (memory sp)))
      :pc (inc pc)
      :sp (inc sp))))

(def operations
  {4 ct
   10 (make-operation +)
   11 (make-operation -)
   12 (make-operation *)
   16 (make-operation #(if (= %1 %2) 1 0))
   25 out})

(defn execute
  [code size]
  (loop [machine (make-machine code size)]
    (let [{:keys [memory pc]} machine
          opcode (memory pc)]
      (if (zero? opcode)
        (println "\nProgram terminated.")
        (if (contains? operations opcode)
          (recur ((operations opcode) machine))
          (throw (ex-info (str "Invalid opcode: " opcode) {})))))))

(execute [4 20 4 20 16 25 0] 10)
