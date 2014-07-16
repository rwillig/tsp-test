(ns tspTester.tspLib
    (:require [geo-graph.graph :as g])
    (:require [tspSolver.ant-colony :as ac])
    (:import tspSolver.ant_colony.AntColonySolution)
    (:require [clojure.java.io :as io])
    (:import java.lang.String)
    (:import java.lang.Math)
    (:require [clojure.math.combinatorics :as combinatorics]))

(defn tsplib-distance
  [from to]
  (if (= from to) nil
  (max 1 (int (. Math (rint (. Math (sqrt (+ (. Math (pow (- (:x to) (:x from)) 2.0)) (. Math (pow (- (:y to) (:y from)) 2.0)))))))))))
 
(defn parse-tsplib-file 
  [filename]
    (with-open [rdr (io/reader filename)]
      (mapv (fn[x](let [r (.split x " ")] (assoc {} :label (nth r 0) :x (read-string (nth r 1)) :y (read-string (nth r 2)))))
      (doall (line-seq rdr)))))

(defprotocol ITestData
  (percent-of-optimal [this]))

(defrecord TspLibGraph [dimension ws opt]
  g/IGraph
    (weight [this row col] (get (:ws this) (if (< row col) [row col] [col row])))
    (dimension [this] (:dimension this))
    (row-col->index [this row col] (+ (* row (:dimension this)) col)))

(extend-type AntColonySolution
  ITestData
    (percent-of-optimal 
      [this]
      (let [trip  (or (:trip this) 0)
            opt   (g/trip (:graph this) (g/vertices->edges (:opt (:graph this))))]
        (+ 1.0 (/ (- trip opt) (* 1.0 opt))))))

(defn concurrent-tsplib-graph
  [stops opt]
  (let [t (filter #(let [[x y] %] (< x y)) (combinatorics/selections (range (count stops)) 2))
        ws (into {} (pmap #(let [[f t] % w (tsplib-distance (nth stops f) (nth stops t))] (assoc {} (vec %) w)) t))]
    (TspLibGraph. (count stops) ws opt)))

(defn tsplib-route 
  [stops opt]
    (concurrent-tsplib-graph stops opt))

(defn tsplib-route-from-file
  [tspfile optfile]
  (let [stops (parse-tsplib-file tspfile)
        opt   (load-file optfile)
        ]
    (tsplib-route stops opt)))
