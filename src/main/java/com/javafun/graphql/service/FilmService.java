package com.javafun.graphql.service;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.coxautodev.graphql.tools.GraphQLResolver;
import com.javafun.graphql.Repository.FilmRepository;
import com.javafun.graphql.model.Actor;
import com.javafun.graphql.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService implements GraphQLResolver<Actor> {

    @Autowired
    private FilmRepository repository;


    public Film getFilm(Actor actor){
        return repository.findById(actor.getFilmId()).get();
    }



}
