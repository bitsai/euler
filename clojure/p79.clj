(ns p79
  (:use [euler :only (fmap find-first timed-test)])
  (:require [clojure.string :as str]))

(defn get-nodes [partial-orders]
  (set (reduce into [] partial-orders)))

(defn get-adjacency-list [partial-orders]
  (let [pairs (mapcat #(partition 2 1 %) partial-orders)
	groups (group-by second pairs)]
    (fmap #(set (map first %)) groups)))

(defn no-edges? [node adjacency-list]
  (zero? (count (adjacency-list node))))

(defn remove-node [node adjacency-list]
  (dissoc (fmap #(disj % node) adjacency-list) node))

(defn topological-sort [nodes adjacency-list]
  (loop [nodes nodes
         adjacency-list adjacency-list
         output []]
    (if-let [n (find-first #(no-edges? % adjacency-list) nodes)]
      (recur (disj nodes n)
             (remove-node n adjacency-list)
             (conj output n))
      (if (empty? adjacency-list)
        output
        (throw (Exception. "cyclic graph!"))))))

(timed-test
 '(\7 \3 \1 \6 \2 \8 \9 \0)
 (let [logins (str/split-lines (slurp "../data/keylog.txt"))
       nodes (get-nodes logins)
       adjacency-list (get-adjacency-list logins)]
   (topological-sort nodes adjacency-list)))
