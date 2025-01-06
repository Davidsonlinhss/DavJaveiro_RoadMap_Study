package com.manning.javapersistence.ch03.validation;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class Item {
    @NotNull // campo não pode ser nulo
    @Size( // comprimento
            min = 2,
            max = 255,
            message = "Name is required, maximum 255 characters."
    )
    private String name; // atributo nome de um item


    @Future
    private Date auctionEnd; // atributo data de término

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getAuctionEnd() {
        return auctionEnd;
    }

    public void setAuctionEnd(Date auctionEnd) {
        this.auctionEnd = auctionEnd;
    }
}
