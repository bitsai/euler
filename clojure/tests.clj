(ns tests
  (:use [utils :only (re-matches?)]))

(let [directory (java.io.File. ".")
      file-names (map #(.getName %) (.listFiles directory))
      is-solution? #(re-matches? #"p\d+\.clj" %)
      solution-names (filter is-solution? file-names)]
  (dorun (map load-file (sort solution-names))))
