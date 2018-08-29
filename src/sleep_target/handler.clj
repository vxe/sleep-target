(ns sleep-target.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s])
  (:use [java-time]))


(def atm--latest-schedule (atom []))

(defn schedule []
  (let  [medication (local-date-time)
         breakfast (plus medication (plus (minutes 30)))
         appearance (plus medication (plus (minutes 90)))
         work-arrive (plus medication (plus (minutes 150)))
         pre-workout-meal (plus medication (plus (minutes 810)))
         workout (plus medication (plus (minutes 840)))
         post-workout-meal (plus medication (plus (minutes 900)))
         writing (plus medication (plus (minutes 960)))
         go-to-sleep (plus medication (plus (minutes 1020)))
         ]
    (reset! atm--latest-schedule
            [medication
             breakfast
             appearance
             work-arrive
             pre-workout-meal
             workout
             post-workout-meal
             writing
             go-to-sleep])))



(def app
  (api
   {:swagger
    {:ui "/"
     :spec "/swagger.json"
     :data {:info {:title "Sleep-target"
                   :description "Compojure Api example"}
            :tags [{:name "api", :description "some apis"}]
            :consumes ["application/json"]
            :produces ["application/json"]}}}

   (context "/api" []
            :tags ["api"]
            (GET "/gen-schedule" []
                 :return String
                 ;; :query-params [x :- Long, y :- Long]
                 :summary "adds two numbers together"
                 (ok (cheshire.core/generate-string (if (schedule)
                                                      "scheduled gen'd"
                                                      "scheduled busted"
                                                      ))))
            (GET "/get-schedule" []
                 :return String
                 ;; :query-params [x :- Long, y :- Long]
                 :summary "adds two numbers together"
                 (ok (cheshire.core/generate-string (map #(.toString %) @atm--latest-schedule)
                                                    )))
            (GET "/get-bedtime" []
                 :return String
                 ;; :query-params [x :- Long, y :- Long]
                 :summary "adds two numbers together"
                 (ok (cheshire.core/generate-string (.toString  (last @atm--latest-schedule))))))))

