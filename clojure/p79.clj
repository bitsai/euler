(ns p79
  (:use [euler :only (timed-test)])
  (:require [clojure.string :as str]))

(defn edges [node adj-list]
  (filter #(= node (first %)) adj-list))

(defn no-incoming? [node adj-list]
  (not-any? #{node} (map second adj-list)))

;; http://en.wikipedia.org/wiki/Topological_sorting#Algorithms
(defn topological-sort [nodes adj-list]
  (loop [S (filter #(no-incoming? % adj-list) nodes)
         L []
         adj-list adj-list]
    (cond (seq S) (let [[n & ns] S
                        es (edges n adj-list)
                        adj-list2 (remove (set es) adj-list)
                        ms (map second es)]
                    (recur (concat ns (filter #(no-incoming? % adj-list2) ms))
                           (conj L n)
                           adj-list2))
          (seq adj-list) (throw (Exception. "Cyclic graph!"))
          :else L)))

(timed-test
 "73162890"
 (let [logins (str/split-lines (slurp "../data/keylog.txt"))
       nodes (distinct (apply concat logins))
       adj-list (distinct (mapcat #(partition 2 1 %) logins))]
   (apply str (topological-sort nodes adj-list))))
