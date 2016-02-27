(ns quil-example.random-walk
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn setup []
  (q/background 255)
  (q/frame-rate 30)
  (q/color-mode :hsb)
  {:x 250 :y 250})

(defn update-state [state]
  (let [choise (q/floor (q/random 4))]
    {:x (+ (:x state)
           (cond
             (= 0 choise) +1
             (= 1 choise) -1
             :else 0))
     :y (+ (:y state)
           (cond
             (= 2 choise) +1
             (= 3 choise) -1
             :else 0))}))

(defn draw-state [state]
  (q/stroke 0)
  (q/point (:x state) (:y state)))

(q/defsketch random-walk
  :title "RandomWalk"
  :size [500 500]
  :setup setup
  :update update-state
  :draw draw-state
  :features [:keep-on-top]
  :middleware [m/fun-mode])
