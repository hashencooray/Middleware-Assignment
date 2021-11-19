package com.example.petstore;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "Search")
public class Search {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

