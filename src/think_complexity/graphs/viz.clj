(ns think-complexity.graphs.viz
  (:require [clojure.set :as s]
            [rhizome.viz :as r]
            [think-complexity.graphs.core :as my-g]))

(defn to-rhizome-graph [my-graph]
  "Function returns a structure easily used by rhizome for graph visualization."
  (let [vs (my-g/vertices my-graph)
        adj-vs (map (fn [v] (my-g/out-vertices my-graph v)) vs)]
    (zipmap vs adj-vs)))

(def g (my-g/create-simple-graph [:v1 :v2 :v3] [#{:v1 :v2} #{:v2 :v3}]))

(def rhizome-graph (to-rhizome-graph g))

(r/view-graph (keys rhizome-graph) rhizome-graph
    :node->descriptor (fn [n] {:label n}))
