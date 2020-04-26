package com.javafun.graphql.model;


import graphql.schema.GraphQLInputType;

public class FilmMutationInput implements GraphQLInputType {
    @Override
    public String getName() {
        return "filmMutationInput";
    }

    public String getFilmName() {
        return filmName;
    }

    String filmName;
    Integer id;

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
