package com.manning.javapersistence.ch03.ex04;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Item {
    Set<Bid> bids = new HashSet<>();

    public Set<Bid> getBids() {
        return Collections.unmodifiableSet(bids);
    }

}
