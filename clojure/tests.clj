(ns tests
  (:use [utils :only (re-matches?)]))

(let [directory (java.io.File. ".")
      names (map #(.getName %) (.listFiles directory))
      is-solution? #(re-matches? #"p\d+\.clj" %)
      solutions (filter is-solution? names)]
  (dorun (map load-file solutions)))
