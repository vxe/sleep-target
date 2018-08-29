 (defproject sleep-target "0.1.0-SNAPSHOT"
   :description "FIXME: write description"
      :dependencies [[org.clojure/clojure "1.8.0"]
                     [metosin/compojure-api "1.1.11"]
                     ;; projects deps
                     [cheshire "5.8.0"]
                     [clj-vxe "0.1.0-SNAPSHOT"]
                     [clojure.java-time "0.3.2"]]
   :ring {:handler sleep-target.handler/app}
   :uberjar-name "server.jar"
   :profiles {:dev {:dependencies [[javax.servlet/javax.servlet-api "3.1.0"]]
                    :plugins [[lein-ring "0.12.0"]]}})
