(defproject project-euler "0.1.75"
  :description "Solving project euler in clojure."
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/math.numeric-tower "0.0.4"]
                 [org.clojure/math.combinatorics "0.1.6"]
                 [org.clojure/algo.generic "0.1.3"]
                 [net.mikera/core.matrix "0.32.1"]]
  :profiles {}
  :local-repo-classpath true
  :release-tasks [["vcs" "assert-committed"]
                  ["change" "version" "leiningen.release/bump-version" "release"]
                  ["vcs" "commit"]
                  ["vcs" "tag" "--no-sign"]
                  ;; creds needed here
                  ;; ["deploy"]
                  ["change" "version" "leiningen.release/bump-version"]
                  ["vcs" "commit"]
                  ["vcs" "push"]])
