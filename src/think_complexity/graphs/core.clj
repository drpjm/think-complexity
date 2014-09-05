(ns think-complexity.graphs.core
  (:require [clojure.set :as s]))

; A graph can be represented as a map of maps data structure:
; vs - set of vertices, which are labels
; es - set of vertex pairs, which are pairs of vertices (sets)
; All graphs generated by this library are assumed to be simple, i.e. no loops, *undirected* edges.

(defn create-simple-graph [vs es]
  (let [new-graph {}]
    (assoc new-graph :V (set vs) :E (set es))))

(defn add-vertex [graph v]
  "Returns a graph with the vertex v added."
  (assoc graph :V (conj (:V graph) v)))

(defn add-edge [graph e]
  "Returns a graph with the edge e added."
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

(defn degree-of-v [graph v]
  (count (out-vertices graph v)))

(defn- edge-set [v vs]
  (set (map (fn [x] #{v x}) vs)))

(defn build-all-edges [graph]
  (loop [curr-es #{} curr-vs (vertices graph)]
    (if (empty? curr-vs)
      curr-es
      (recur (s/union curr-es (edge-set (first curr-vs) (rest curr-vs))) (rest curr-vs)))
    ))

(defn add-all-edges [graph]
  "Creates a complete graph over all vertices."
  (let [new-edges (build-all-edges graph)]
    (assoc graph
      :E
      new-edges)))

(defn add-regular-edges [graph degree]
  "Takes an edgeless graph and generates a new graph such that each
  vertex has the supplied degree."
  (if (>= degree (count (vertices graph)))
    (println "Error: not enough vertices for required degree.")
    (if (odd? degree)
      (if (odd? (count (vertices graph)))
        (println "Error: cannot have an odd number of vertices with odd degree.")
        (println "Generate regular graph with " (count (vertices graph)) " vertices and degree = " degree ".")
        )
      (do ; even degree!
        (println "Generate regular graph.")))))

(def t (map (fn [dest] #{:v1 dest}) [:v3 :v2]))

(def test-graph2
  (create-simple-graph  [:v1 :v2 :v3] []))

(def reg-test-graph2 (add-regular-edges test-graph2 2))
