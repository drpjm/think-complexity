(ns think-complexity.chapter02.core)

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

(defn get-edge [graph v1 v2]
  "This function returns the edge that connects v1 and v2, or nil
  if it does not exist."
  (if (has-edge? graph v1 v2)
    #{v1 v2}
    nil))

(defn remove-edge [graph v1 v2]
  (if (has-edge? graph v1 v2)
    (assoc graph :E (disj (:E graph) #{v1 v2}))
    graph))

