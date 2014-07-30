(ns think-complexity.chapter02.core-test
  (:require [think-complexity.chapter02.core :refer :all]))

(def new-graph
  (create-graph [:v1 :v2] [[:v1 :v2] [:v2 :v2]]))

