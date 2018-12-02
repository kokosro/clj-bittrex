(ns clj-bittrex.core
  (:require [clj-bittrex.request :as request])
  (:gen-class))

(defn getbalances
  [options]
  (request/account :getbalances
                   {}
                   options))

(defn getbalance
  [options asset]
  (request/account :getbalance
                   {:currency (name asset)}
                   options))

(defn getdepositaddress
  [options asset]
  (request/account :getdepositaddress
                   {:currency (name asset)}
                   options))

(defn withdraw
  [options asset quantity address & [paymentid]]
  (request/account :withdraw
                   {:currency (name asset)
                    :quantity (if (string? quantity) quantity
                                  (format "%.8f" quantity))
                    :address address
                    :paymentid (or paymentid "")}
                   options))

(defn getorder
  [options uuid]
  (request/account :getorder
                   {:uuid uuid}
                   options))

(defn getorderhistory
  [options & [market]]
  (request/account :getorderhistory
                   (if-not (nil? market)
                     {:market (name market)}
                     {})
                   options))

(defn getwithdrawalhistory
  [options & [currency]]
  (request/account :getwithdrawalhistory
                   (if-not (nil? currency)
                     {:currency (name currency)}
                     {})
                   options))

(defn getdeposithistory
  [options & [currency]]
  (request/account :getdeposithistory
                   (if-not (nil? currency)
                     {:currency (name currency)}
                     {})
                   options))

(defn buylimit
  [options market quantity rate]
  (request/market :buylimit
                  {:market (name market)
                   :quantity (if (string? quantity)
                               quantity
                               (format "%.8f" quantity))
                   :rate (if (string? rate)
                               rate
                               (format "%.8f" rate))}
                  options))

(defn selllimit
  [options market quantity rate]
  (request/market :buylimit
                  {:market (name market)
                   :quantity (if (string? quantity)
                               quantity
                               (format "%.8f" quantity))
                   :rate (if (string? rate)
                               rate
                               (format "%.8f" rate))}
                  options))

(defn cancel
  [options uuid]
  (request/market :cancel
                  {:uuid uuid}
                  options))

(defn getopenorders
  [options & [market]]
  (request/market :getopenorders
                  (if-not (nil? market)
                    {:market (name market)}
                    {})
                  options))

(defn getmarkets
  [options]
  (request/public :getmarkets
                  {}
                  options))

(defn getcurrencies
  [options]
  (request/public :getcurrencies
                  {}
                  options))

(defn getticker
  [options market]
  (request/public :getticker
                  {:market (name market)}
                  options))

(defn getmarketsummaries
  [options]
  (request/public :getmarketsummaries
                  {}
                  options))

(defn getmarketsummary
  [options market]
  (request/public :getmarketsummary
                  {:market (name market)}
                  options))

(defn getorderbook
  [options market & [type]]
  (request/public :getorderbook
                  {:market (name market)
                   :type (name (or type :both))}
                  options))

(defn getmarkethistory
  [options market]
  (request/public :getmarkethistory
                  {:market (name market)}
                  options))
