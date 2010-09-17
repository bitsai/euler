(ns p79
  (use [utils :only (map-vals timed-test)])
  (use [clojure.contrib.graph :only (dependency-list)]))

(def traces ["319"
	     "680"
	     "180"
	     "690"
	     "129"
	     "620"
	     "762"
	     "689"
	     "762"
	     "318"
	     "368"
	     "710"
	     "720"
	     "710"
	     "629"
	     "168"
	     "160"
	     "689"
	     "716"
	     "731"
	     "736"
	     "729"
	     "316"
	     "729"
	     "729"
	     "710"
	     "769"
	     "290"
	     "719"
	     "680"
	     "318"
	     "389"
	     "162"
	     "289"
	     "162"
	     "718"
	     "729"
	     "319"
	     "790"
	     "680"
	     "890"
	     "362"
	     "319"
	     "760"
	     "316"
	     "729"
	     "380"
	     "319"
	     "728"
	     "716"])

(def nodes (apply concat traces))

(def neighbors
  (let [pairs (mapcat #(partition 2 1 %) traces)
	grouped (group-by second pairs)]
    (map-vals #(map first %) grouped)))

(def graph {:nodes nodes :neighbors neighbors})

(timed-test
 "Problem 79"
 "73162890"
 (apply str (apply concat (dependency-list graph))))
