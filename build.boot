(set-env!
  :dependencies '[[adzerk/boot-cljs          "2.1.4"]
                  [adzerk/boot-reload        "0.5.2"]
                  [hoplon/hoplon             "7.1.0-SNAPSHOT"]
                  [org.clojure/clojure       "1.9.0-beta3"]
                  [org.clojure/core.async    "0.3.443"]
                  [org.clojure/clojurescript "1.9.946"]
                  [tailrecursion/boot-jetty  "0.1.3"]]
  :source-paths #{"src"}
  :asset-paths  #{"assets"})

(require
  '[adzerk.boot-cljs         :refer [cljs]]
  '[adzerk.boot-reload       :refer [reload]]
  '[hoplon.boot-hoplon       :refer [hoplon prerender]]
  '[tailrecursion.boot-jetty :refer [serve]])

(deftask dev
  "Build reproducible for local development."
  []
  (comp
    (watch)
    (speak)
    (hoplon)
    (reload)
    (cljs)
    (serve :port 8080)))

(deftask prod
  "Build reproducible for production deployment."
  []
  (comp
    (hoplon)
    (cljs :optimizations :advanced)
    (target :dir #{"target"})))
