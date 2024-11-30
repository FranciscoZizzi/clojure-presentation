(ns user
  (:require [nextjournal.clerk :as clerk]))

;; Build a html file from the given notebook notebooks.
;; See the docstring for more options.
(defn -main []
  (clerk/build! {:paths ["notebooks/slideshow.clj"]}))

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