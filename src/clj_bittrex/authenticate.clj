(ns clj-bittrex.authenticate
  (:import (javax.crypto Mac)
           (javax.crypto.spec SecretKeySpec)
           )
  (:gen-class))



(defn secretKeyInst [key mac]
    (SecretKeySpec. (.getBytes key) (.getAlgorithm mac)))

(defn toString [bytes]
    "Convert bytes to a String"
    (String. bytes "UTF-8"))

(defn toHexString [bytes]
  "Convert bytes to a String"
  (apply str (map #(format "%02x" %) bytes)))

(defn sha512sign [key string]
  "Returns the signature of a string with a given
    key, using a SHA-512 HMAC."
  (let [mac (Mac/getInstance "HmacSHA512")
        secretKey (secretKeyInst key mac)]
    (-> (doto mac
          (.init secretKey)
          (.update (.getBytes string)))
        .doFinal)))

(defn sign
  [uri secret]
  (toHexString (sha512sign secret uri)))
