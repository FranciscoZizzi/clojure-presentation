(ns user
  (:require [nextjournal.clerk :as clerk]
            [clojure.java.io :as io]
            [clojure.string :as str]))

;; I didn't know how to change the css of the main body of the notebook
;; So I just modify the built html with the css property that I wanted
;; There was scroll on every slide, and it made me ANGRY
(defn add-css-property "Takes the file path, looks for body { ... } and adds overflow: hidden" [file-path]
  (let [html-content (slurp file-path)
        updated-content (str/replace html-content
                                     #"body \{([^\}]+)\}"
                                     (fn [[full-match inner-content]]
                                       (if (str/includes? inner-content "overflow: hidden;")
                                         full-match
                                         (str "body {" inner-content "  overflow: hidden;\n}"))))]
    (spit file-path updated-content)))

;; Makes the code viewer box an appropriate width
(defn remove-max-code-viewer-width [file-path]
  (let [html-content (slurp file-path)
        updated-content (str/replace html-content ".max-w-wide  { @apply max-w-3xl !important; }" "")]
    (spit file-path updated-content)))

(defn -main []
  (do
    (clerk/build! {:paths ["notebooks/slideshow.clj"]}))
    (add-css-property "public/build/index.html")
    (remove-max-code-viewer-width "public/build/index.html")
    )

(defn dev []
  (clerk/show! "notebooks/slideshow.clj")
  )