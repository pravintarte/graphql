package com.javafun.graphql.service;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import com.javafun.graphql.Repository.FilmRepository;
import com.javafun.graphql.model.Film;
import com.javafun.graphql.model.FilmMutationInput;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import javax.transaction.Transactional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class FilmMutationService implements GraphQLMutationResolver, GraphQLSubscriptionResolver {

    @Autowired
    private FilmRepository repository;
    private ConcurrentHashMap<Integer, FluxSink<Film>> subscribers = new ConcurrentHashMap<>();

    @Transactional
    public Film updateFilmName(Integer id,String name){
        Film film = repository.getOne(id);
        film.setName(name);
        repository.save(film);
        if(subscribers.get(id)!=null){
            subscribers.get(id).next(film);
        }
        return film;
    }

    @Transactional
    public Film updateFilmNameByInputObject(FilmMutationInput input){
        Film film = repository.getOne(input.getId());
        film.setName(input.getFilmName());
        repository.save(film);
        if(subscribers.get(input.getId())!=null){
            subscribers.get(input.getId()).next(film);
        }
        return film;
    }

    public Publisher<Film> onFilmUpdate(Integer filmId){
        return Flux.create(subscriber-> subscribers.put(filmId,subscriber.onDispose(()->subscribers.remove(filmId,subscriber))), FluxSink.OverflowStrategy.LATEST);
    }

}
