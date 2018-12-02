# clj-bittrex
[![Build Status](https://travis-ci.org/kokosro/clj-bittrex.svg?branch=master)](https://travis-ci.org/kokosro/clj-bittrex)
[![codecov](https://codecov.io/gh/kokosro/clj-bittrex/branch/master/graph/badge.svg)](https://codecov.io/gh/kokosro/clj-bittrex)
[![Clojars Project](https://img.shields.io/clojars/v/org.clojars.kokos/clj-bittrex.svg)](https://clojars.org/org.clojars.kokos/clj-bittrex)

A clojure library for communicating with bittrex rest api.

```clj
[org.clojars.kokos/clj-bittrex "0.0.0"]
```

## Usage
all functions in clj-bittrex.core require a configuration object as first argument
```clj
{:key "...."
 :secret "..."
 :url "https://api.bittrex.com/api/v1.1"}
```
## License

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
