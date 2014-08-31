(ns think-complexity.graphs.core-test
  (:require [think-complexity.chapter02.core :refer :all]))

(def test-graph
  (create-simple-graph  [:v1 :v2 :v3] [#{:v1 :v2} #{:v2 :v3} #{:v1 :v3}]))

(map edge-as-string (:E test-graph))

(out-vertices test-graph :v1)

(out-edges test-graph :v2)
