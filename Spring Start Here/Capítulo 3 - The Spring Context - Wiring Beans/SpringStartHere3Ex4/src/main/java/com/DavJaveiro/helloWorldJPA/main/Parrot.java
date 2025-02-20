package com.DavJaveiro.helloWorldJPA.main;

import org.springframework.stereotype.Component;

@Component
public class Parrot {
    private String name = "Koko";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Parrot : " + name;
    }
}
