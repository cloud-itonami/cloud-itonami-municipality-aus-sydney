(ns ordinance.facts-test
  (:require [kotoba.lang.text :as str]
            [clojure.test :refer [deftest is]]
            [ordinance.facts :as facts]))

(deftest sydney-has-spec-basis
  (let [sb (facts/spec-basis "sydney")]
    (is (= 2 (count sb)))
    (is (every? #(str/starts-with? (:ordinance/url %) "https://www.cityofsydney.nsw.gov.au/") sb))))

(deftest unknown-municipality-has-no-spec-basis
  (is (nil? (facts/spec-basis "melbourne")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["sydney" "melbourne"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["melbourne"] (:missing-municipalities c)))))

(deftest by-topic-filters
  (is (= ["sydney.local-approvals-policy-temporary-structures"]
         (mapv :ordinance/id (facts/by-topic "sydney" :construction))))
  (is (empty? (facts/by-topic "sydney" :labor)))
  (is (empty? (facts/by-topic "melbourne" :ethics))))
