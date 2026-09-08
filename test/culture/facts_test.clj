(ns culture.facts-test
  (:require [clojure.edn :as edn]
            [kotoba.lang.text :as str]
            [clojure.test :refer [deftest is]]
            [culture.facts :as facts]))

(deftest sydney-has-culture-basis
  (let [sb (facts/spec-basis "sydney")]
    (is (= 9 (count sb)))
    (is (= (count sb) (count (set (map :culture/id sb)))))
    (is (every? #(str/starts-with? (:culture/url %) "https://") sb))
    (is (every? #(= "sydney" (:culture/municipality %)) sb))
    (is (every? #(= "AUS" (:culture/country %)) sb))
    (is (every? #(seq (:culture/summary %)) sb))
    (is (every? #(string? (:culture/retrieved-at %)) sb))))

(deftest unknown-municipality-has-no-basis
  (is (nil? (facts/spec-basis "melbourne")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["sydney" "melbourne"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["melbourne"] (:missing-municipalities c)))))

(deftest by-kind-filters
  (is (= 3 (count (facts/by-kind "sydney" :dish))))
  (is (= ["sydney.product.sydney-rock-oyster"]
         (mapv :culture/id (facts/by-kind "sydney" :product))))
  (is (empty? (facts/by-kind "sydney" :craft)))
  (is (empty? (facts/by-kind "melbourne" :dish))))

(deftest tx-file-matches-catalog
  (let [tx (edn/read-string (slurp "data/culture-tx.edn"))
        flat (mapcat val (sort-by key facts/catalog))]
    (is (= (vec flat) (vec tx)))))
