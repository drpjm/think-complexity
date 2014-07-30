(ns think-complexity.chapter02.core)

; A graph can be represented as a map of maps data structure:
; vs - set of vertices
; es - set of vertex pairs
(defn create-graph [vs es]
  (let [new-graph {}]
    (assoc new-graph :V (set vs) :E (set es))))

(defn add-vertex [graph v]
  (assoc graph :V (conj (:V graph) v)))

(defn add-edge [graph e]
  (assoc graph :E (conj (:E graph) e)))
