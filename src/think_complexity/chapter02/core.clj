(ns think-complexity.chapter02.core
  (:require [clojure.set :as s]))

; A graph can be represented as a map of maps data structure:
; vs - set of vertices, which are labels
; es - set of vertex pairs, which are pairs of vertices
(defn create-simple-graph [vs es]
  (let [new-graph {}]
    (assoc new-graph :V (set vs) :E (set es))))

(defn add-vertex [graph v]
  (assoc graph :V (conj (:V graph) v)))

(defn add-edge [graph e]
  (assoc graph :E (conj (:E graph) e)))

(defn edge-as-string [e]
  (str (first e) " -> " (last e)))

(defn has-edge? [graph v1 v2]
  (if (contains? (:E graph) #{v1 v2})
    true
    false))

(defn has-vertex? [graph v]
  (if (contains? (:V graph) v)
    true
    false))

(defn get-edge [graph v1 v2]
  "This function returns the edge that connects v1 and v2, or nil
  if it does not exist."
  (if (has-edge? graph v1 v2)
    #{v1 v2}
    nil))

(defn remove-edge [graph v1 v2]
  "Removes an edge from the supplied graph."
  (if (has-edge? graph v1 v2)
    (assoc graph :E (disj (:E graph) #{v1 v2}))
    graph))

(defn vertices [graph]
  "Convenience function for extracting the graph's vertices."
  (:V graph))

(defn edges [graph]
  "Convenience function for extracting the graph's edges."
  (:E graph))

(defn out-edges [graph v]
  "Returns a list of edges attached to vertex v."
  (filter (fn [e] (if (contains? e v)
                    true
                    false))
          (:E graph)))

(defn out-vertices [graph v]
  "Returns a list of vertices that are adjacent to v."
  (let [es (out-edges graph v)]
    (map first (map (fn [e] (s/difference e #{v}))
         es))))

(defn edge-set [v vs]
  (map (fn [x] #{v x}) vs))

(defn add-all-edges [graph]
  "Creates a complete graph over all vertices."
  ;(assoc graph
  ;  :E
  ;  )
  )

(def empty-graph (create-simple-graph [:v1 :v2 :v3] #{}))

;(edge-set :v1 #{:v2 :v3})
