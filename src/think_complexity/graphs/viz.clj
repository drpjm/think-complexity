(ns think-complexity.graphs.viz
  (:require [clojure.set :as s]
            [rhizome.viz :as r]
            [think-complexity.graphs.core :as my-g]))

(def g (my-g/create-simple-graph [:v1 :v2 :v3 :v4 ] []))

(defn- to-rhizome-graph [my-graph]
  (loop [rhizome-graph {}
         my-graph-vs (my-g/vertices my-graph)]
    (if (empty? my-graph-vs)
      rhizome-graph
      (let [curr-node (first my-graph-vs)
            out-vs (my-g/out-vertices my-graph curr-node)
            new-dests (filter (fn [v] (not (contains? rhizome-graph v))) out-vs)]
        (recur
         (assoc rhizome-graph curr-node new-dests)
         (disj my-graph-vs curr-node))))))

(defn view-graph [my-graph]
  (r/view-graph (keys (to-rhizome-graph my-graph)) (to-rhizome-graph my-graph)
    :node->descriptor (fn [n] {:label n})
    :edge->descriptor (fn [s d] {:arrowhead "none"})))

(def test-graph2
  (my-g/create-simple-graph  [:v1 :v2 :v3 :v4] []))

;(def reg-test-graph2 (my-g/add-regular-edges test-graph2 1))

;(view-graph reg-test-graph2)
