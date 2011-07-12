(ns tests
  (:use [clojure.java.io :only (file)]))

(let [file-names (map #(.getName %) (.listFiles (file ".")))
      is-solution? (fn [name] (re-matches #"p\d+\.clj" name))
      solution-names (filter is-solution? file-names)]
  (dorun (map load-file (sort solution-names))))
