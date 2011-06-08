(ns tests)

(let [directory (java.io.File. ".")
      file-names (map #(.getName %) (.listFiles directory))
      is-solution? (fn [name] (re-matches #"p\d+\.clj" name))
      solution-names (filter is-solution? file-names)]
  (dorun (map load-file (sort solution-names))))
