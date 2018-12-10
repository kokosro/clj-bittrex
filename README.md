# clj-bittrex
[![Clojars Project](https://img.shields.io/clojars/v/org.clojars.kokos/clj-bittrex.svg)](https://clojars.org/org.clojars.kokos/clj-bittrex)

A clojure library for communicating with bittrex rest api.

```clj
[org.clojars.kokos/clj-bittrex "0.1.1"]
```

## Usage
all functions in clj-bittrex.core require a configuration object as first argument
```clj
 
 (ns example
  (:require [clj-bittrex.core :as bittrex])
  (:gen-class))

(def conf 
 {:key "...."
  :secret "..."
  :url "https://api.bittrex.com/api/v1.1"})

(defn load-portfolio
  []
  (reduce
   (fn [r c]
     (if (< 0.0 (:Balance c))
         (assoc r (keyword (:Currency c))
            {:balance (:Available c)
             :pending (:Pending c)
             :deposit-at (:CryptoAddress c)})
         r))
   {}
   (bittrex/getbalances con))))

 
```
## License

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
