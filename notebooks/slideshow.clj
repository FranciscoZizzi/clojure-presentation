^{::clerk/visibility {:code :hide :result :hide}}
(ns slideshow
  (:require [nextjournal.clerk :as clerk]
            [clojure.java.io :as io]
            [nextjournal.clerk-slideshow :as slideshow])
  (:import [java.net URL]
           [java.nio.file Paths Files]
           [java.awt.image BufferedImage]
           [javax.imageio ImageIO]))
{::clerk/visibility {:code :hide :result :hide}}
(clerk/add-viewers! [slideshow/viewer])
;; # Hello!