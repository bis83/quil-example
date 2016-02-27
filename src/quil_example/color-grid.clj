(ns quil-example.color-grid
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn setup []
  (q/background 0)
  (q/frame-rate 30)
  (q/color-mode :hsb 500 500 100)
  {:step-x 25
   :step-y 25})

(defn update [state]
  {:step-x (max (q/mouse-x) 5)
   :step-y (max (q/mouse-y) 5)})

(defn draw [state]
  (loop [grid-x 0]
    (loop [grid-y 0]
      (q/fill grid-x (- (q/height) grid-y) 100)
      (q/stroke grid-x (- (q/height) grid-y) 98)
      (q/rect grid-x grid-y (:step-x state) (:step-y state))
      (if (< grid-y (q/height))
        (recur (+ (:step-y state) grid-y))))
    (if (< grid-x (q/width))
      (recur (+ (:step-x state) grid-x)))))

(q/defsketch color-grid
  :title "ColorGrid"
  :size [500 500]
  :setup setup
  :update update
  :draw draw
  :features [:keep-on-top]
  :middleware [m/fun-mode])
