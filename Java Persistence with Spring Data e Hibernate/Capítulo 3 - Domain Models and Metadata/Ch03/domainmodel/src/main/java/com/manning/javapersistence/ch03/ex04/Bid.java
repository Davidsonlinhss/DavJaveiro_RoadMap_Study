package com.manning.javapersistence.ch03.ex04;

public class Bid {

    private Item item; // associação com Item

    public Bid() {
    }

    public Bid(Item item) {
        this.item = item;
        item.bids.add(this); // Bidirectional
    }

    public Item getItem() {
        return item;
    }

}
