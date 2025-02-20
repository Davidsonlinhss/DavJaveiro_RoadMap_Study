package com.DavJaveiro.helloWorldJPA.main;

import org.springframework.stereotype.Component;

public class Parrot {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Parrot() {
        System.out.println("Parrot Created");
    }

    public String toString() {
        return "Parrot : " + name;
    }
}
