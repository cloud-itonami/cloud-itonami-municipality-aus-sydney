(ns ordinance.facts
  "Municipal-ordinance compliance catalog for Sydney -- the ELEVENTH
  municipality-level entry (see cloud-itonami-municipality-jpn-tokyo,
  -usa-washington-dc, -gbr-london, -can-toronto, -deu-berlin, -fra-paris,
  -nld-amsterdam, -esp-madrid, -kor-seoul, -ita-roma for the first ten)
  per ADR-2607141700 (cloud-itonami-compliance-fact-federation).

  Every entry cites an OFFICIAL cityofsydney.nsw.gov.au URL -- never
  fabricated. An ordinance not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url/number.

  Direct PDF links found via search all 404'd/403'd; both entries below
  were instead directly WebFetch-verified against cityofsydney.nsw.gov.au's
  own HTML policy pages, which state Published/Last modified dates
  explicitly.")

(def catalog
  "municipality-slug -> vector of ordinance entries."
  {"sydney"
   [{:ordinance/id "sydney.code-of-conduct"
     :ordinance/title "Code of Conduct"
     :ordinance/municipality "sydney"
     :ordinance/country "AUS"
     :ordinance/kind :ordinance
     :ordinance/url "https://www.cityofsydney.nsw.gov.au/policies/code-of-conduct"
     :ordinance/url-provenance :official-cityofsydney-nsw-gov-au
     :ordinance/enacted-date "2024-10-10"
     :ordinance/last-revised-date "2024-10-21"
     :ordinance/retrieved-at "2026-07-15"
     :ordinance/topic #{:ethics :governance}}
    {:ordinance/id "sydney.local-approvals-policy-temporary-structures"
     :ordinance/title "Local approvals policy for construction-related temporary structures on and above roads"
     :ordinance/municipality "sydney"
     :ordinance/country "AUS"
     :ordinance/kind :ordinance
     :ordinance/url "https://www.cityofsydney.nsw.gov.au/policies/local-approvals-policy-construction-related-temporary-structures-on-above-roads"
     :ordinance/url-provenance :official-cityofsydney-nsw-gov-au
     :ordinance/enacted-date "2022-11-21"
     :ordinance/last-revised-date "2025-11-11"
     :ordinance/retrieved-at "2026-07-15"
     :ordinance/topic #{:licensing :construction}}]})

(defn spec-basis [muni] (get catalog muni))

(defn coverage
  ([] (coverage (keys catalog)))
  ([munis]
   (let [have (filter catalog munis)
         missing (remove catalog munis)]
     {:requested (count munis)
      :covered (count have)
      :covered-municipalities (vec (sort have))
      :missing-municipalities (vec (sort missing))
      :note (str "cloud-itonami-municipality-aus-sydney Wave 0 (ADR-2607141700): "
                 (count (get catalog "sydney")) " Sydney entries seeded with "
                 "an official cityofsydney.nsw.gov.au citation. Extend "
                 "`ordinance.facts/catalog`, never fabricate an id/url.")})))

(defn by-topic [muni topic]
  (filterv #(contains? (:ordinance/topic %) topic) (spec-basis muni)))
