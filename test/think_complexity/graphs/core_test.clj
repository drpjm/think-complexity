(ns think-complexity.graphs.core-test
  (:require [think-complexity.graphs.core :refer :all]
            [think-complexity.graphs.viz :as viz]))

(def test-graph1
  (create-simple-graph  [:v1 :v2 :v3] [#{:v1 :v2} #{:v2 :v3} #{:v1 :v3}]))

(def test-graph2
  (create-simple-graph  [:v1 :v2 :v3 :v4] []))

;(def reg-test-graph2 (add-regular-edges test-graph2 2))

;(map edge-as-string (:E test-graph1))

;(out-vertices test-graph1 :v1)

;(out-edges test-graph1 :v2)

(viz/view-graph test-graph2)
