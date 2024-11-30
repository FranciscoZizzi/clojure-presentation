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
                                       ;; Check if "overflow: hidden;" exists in the inner content
                                       (if (str/includes? inner-content "overflow: hidden;")
                                         full-match
                                         ;; Add "overflow: hidden;" before closing "}"
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
  ;; start Clerk's built-in webserver on the default port 7776, opening the browser when done
  (clerk/serve! {:browse true})

  ;; either call `clerk/show!` explicitly
  (clerk/show! "notebooks/slideshow.clj")

  ;; or let Clerk watch the given `:paths` for changes
  (clerk/serve! {:watch-paths ["notebooks" "src"]})

  ;; start with watcher and show filter function to enable notebook pinning
  (clerk/serve! {:watch-paths ["notebooks" "src"] :show-filter-fn #(clojure.string/starts-with? % "notebooks")})
  )