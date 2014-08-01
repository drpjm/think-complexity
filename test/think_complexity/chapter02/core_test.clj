(ns think-complexity.chapter02.core-test
  (:require [think-complexity.chapter02.core :refer :all]))

(def test-graph
  (create-simple-graph  [:v1 :v2] [#{:v1 :v2} #{:v2 :v3}]))

(map edge-as-string (:E test-graph))
