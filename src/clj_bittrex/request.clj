(ns clj-bittrex.request
  (:require [clj-bittrex.authenticate :as auth]
            [clj-http.client :as http]
            [clojure.data.json :as json])
  (:import (java.net URLEncoder))
  (:gen-class))

(defn make-params
  [data]
  (reduce (fn [r [k v]]
            (if (= "" r)
              (str r (name k) "=" (URLEncoder/encode (str v) "UTF-8"))
              (str r "&" (name k) "=" (URLEncoder/encode (str v) "UTF-8"))))
          ""
          data))

(defn create-nonce-generator
  [& [start-from]]
  (let [start-from (or start-from (System/currentTimeMillis))
        nonce-holder (atom start-from)]
    (fn []

      (locking nonce-holder
        (let [nonce-window 2000;
              now (System/currentTimeMillis)
              new-nonce (if (< @nonce-holder (- now nonce-window))
                          now
                          (+ @nonce-holder 10))]
          (reset! nonce-holder new-nonce))))))

(def nonce-generator (create-nonce-generator))



(defn general
  [method-type method data options]
  (let [nonce (nonce-generator)
        req-data (merge
                   {:apikey (:key options)}
                  data
                  {:nonce nonce})
        url (str (:url options)
                 "/" (name method-type) "/"  (name method) "?" (make-params req-data))
        signature (auth/sign url (:secret options))
        r (json/read-str
           (:body (http/request {:timeout 1000
                                 :method :get
                                 :url url
                                 :insecure? true
                                 :headers {"apisign" signature}}))
           :key-fn #'keyword)]
    (if (and (contains? r :success)
             (:success r))
      (:result r)
      {:error (:message r)})))



(def account (partial general :account))

(def market (partial general :market))

(def public (partial general :public))
