(ns gift-exchange-test
  (:require [clojure.test :refer :all]
            [gift-exchange :refer :all]))

(deftest it-works
	(testing "finds correct number of matches"
		(is (= (count (vals (create-gift-exchange ["aidan" "eileen" "kaitlin" "dan" "mom"] ))) 5))))

