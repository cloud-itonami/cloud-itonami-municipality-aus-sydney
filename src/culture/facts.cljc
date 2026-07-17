(ns culture.facts
  "Regional-culture catalog for Sydney -- local dishes, protected
  products, beverages, festivals and heritage sites, piggybacked onto
  this municipality compliance repo per ADR-2607171400
  (cloud-itonami-municipality-culture-catalog, in com-junkawasaki/root),
  sibling namespace to `ordinance.facts` (ADR-2607141700).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "municipality-slug -> vector of culture entries."
  {"sydney"
   [{:culture/id "sydney.dish.meat-pie"
     :culture/name "Meat pie"
     :culture/municipality "sydney"
     :culture/country "AUS"
     :culture/kind :dish
     :culture/summary "Handheld savoury pie of diced meat and gravy, described in 2003 by the premier of New South Wales as Australia's national dish."
     :culture/url "https://en.wikipedia.org/wiki/Meat_pie_(Australia_and_New_Zealand)"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "sydney.dish.lamington"
     :culture/name "Lamington"
     :culture/municipality "sydney"
     :culture/country "AUS"
     :culture/kind :dish
     :culture/summary "Australian cake of butter or sponge cake squares coated in chocolate sauce and desiccated coconut; 21 July is National Lamington Day in Australia."
     :culture/url "https://en.wikipedia.org/wiki/Lamington"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "sydney.dish.anzac-biscuit"
     :culture/name "Anzac biscuit"
     :culture/municipality "sydney"
     :culture/country "AUS"
     :culture/kind :dish
     :culture/summary "Sweet oat-based biscuit popular in Australia and New Zealand, with historical ties to both nations' military heritage."
     :culture/url "https://en.wikipedia.org/wiki/Anzac_biscuit"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "sydney.beverage.flat-white"
     :culture/name "Flat white"
     :culture/municipality "sydney"
     :culture/country "AUS"
     :culture/kind :beverage
     :culture/summary "Espresso-and-steamed-milk coffee whose invention is claimed by café owners in both Australia and New Zealand, with documented evidence from Sydney in the early 1980s."
     :culture/url "https://en.wikipedia.org/wiki/Flat_white"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "sydney.product.sydney-rock-oyster"
     :culture/name "Sydney rock oyster"
     :culture/municipality "sydney"
     :culture/country "AUS"
     :culture/kind :product
     :culture/summary "Oyster species (Saccostrea glomerata) native to Australia and New Zealand, farmed commercially in New South Wales and southern Queensland."
     :culture/url "https://en.wikipedia.org/wiki/Sydney_rock_oyster"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "sydney.festival.vivid-sydney"
     :culture/name "Vivid Sydney"
     :culture/municipality "sydney"
     :culture/country "AUS"
     :culture/kind :festival
     :culture/summary "Annual festival of light installations, musical performances and creative discussions held in Sydney over three weeks in May and June."
     :culture/url "https://en.wikipedia.org/wiki/Vivid_Sydney"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "sydney.festival.mardi-gras"
     :culture/name "Sydney Gay and Lesbian Mardi Gras"
     :culture/municipality "sydney"
     :culture/country "AUS"
     :culture/kind :festival
     :culture/summary "Annual pride parade in Sydney attended by hundreds of thousands of people, the largest gay and lesbian event in Oceania."
     :culture/url "https://en.wikipedia.org/wiki/Sydney_Gay_and_Lesbian_Mardi_Gras"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "sydney.heritage.sydney-opera-house"
     :culture/name "Sydney Opera House"
     :culture/municipality "sydney"
     :culture/country "AUS"
     :culture/kind :heritage
     :culture/summary "Performing-arts centre in Sydney that became a UNESCO World Heritage Site in 2007."
     :culture/url "https://en.wikipedia.org/wiki/Sydney_Opera_House"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "sydney.heritage.sydney-harbour-bridge"
     :culture/name "Sydney Harbour Bridge"
     :culture/municipality "sydney"
     :culture/country "AUS"
     :culture/kind :heritage
     :culture/summary "Arch bridge across Sydney Harbour, opened in 1932 and nicknamed the Coathanger for its arch-based design."
     :culture/url "https://en.wikipedia.org/wiki/Sydney_Harbour_Bridge"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

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
      :note (str "cloud-itonami-municipality-aus-sydney culture catalog "
                 "(ADR-2607171400): " (count (get catalog "sydney"))
                 " Sydney entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [muni kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis muni)))
