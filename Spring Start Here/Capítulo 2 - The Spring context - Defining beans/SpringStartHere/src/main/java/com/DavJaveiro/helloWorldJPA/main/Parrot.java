package com.DavJaveiro.helloWorldJPA.main;

import org.springframework.stereotype.Component;

@Component // dizendo ao Spring para add instância dessa classe ao context
public class Parrot {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
