(defproject tsp-test "0.0.1"
  :description "tsp lib tests for ant-colony efficacy"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins  [[s3-wagon-private "1.1.2"]]
  :repositories  {"private"  {:url "s3p://rsw-hdfs/releases/" 
                                :passphrase :env 
                                :username :env}}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojars.raywillig/geo-cache "0.0.1"] 
                 [org.clojure/math.combinatorics "0.0.4"]
                 [tsp "2.0.0"]])
