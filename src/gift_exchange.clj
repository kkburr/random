(ns gift-exchange)
(require '[clojure.java.io :as io])

(defn find-recipient [names exchange-hash gifter]
    (loop [int (rand-int (count names))]
      (let [recipient (get names int)] 
        (if (and (not (= recipient gifter)) (not (some #(= recipient %) (vals exchange-hash))))
          recipient
          (recur (rand-int (count names)))))))

(defn print-csv
  [names]
(println names)
  (spit "gift-exchange.csv" (clojure.string/join "\n"  (map #(clojure.string/join "," %1) names))))	  

(defn already-in-hash 
  [exchange-hash name]
  (some #(= name %) (keys exchange-hash)))

(defn create-gift-exchange
  ([names] (create-gift-exchange names {}))
  ([names pre-selected]
    (loop [current-count 0
           exchange-hash pre-selected]
      (if (>= current-count (count names))
        (print-csv exchange-hash)
	(if (already-in-hash exchange-hash (get names current-count))
	    (recur (+ 1 current-count) exchange-hash)
	    (let [gifter (get names current-count)
                  recipient (find-recipient names exchange-hash gifter)
                  new-exchange-hash (assoc exchange-hash gifter recipient)]
	  	(recur (+ 1 current-count) new-exchange-hash)))))))
