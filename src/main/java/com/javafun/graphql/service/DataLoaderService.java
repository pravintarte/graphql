package com.javafun.graphql.service;

import com.javafun.graphql.Repository.ActorRepository;
import com.javafun.graphql.Repository.FilmRepository;
import com.javafun.graphql.model.Actor;
import com.javafun.graphql.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

@Service
public class DataLoaderService {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private ActorRepository actorRepository;

    @PostConstruct
    public void loadData(){
        String [] actors = {"ShahRukh Khan","Salman Khan","Aamir Khan","Amitabh Bacchan"};
        Map<String,String> films = new HashMap<String, String>(){
            {
                put("ShahRukh Khan","Kuch Kuch Hota Hai");
                put("Salman Khan","Dabang");
                put("Aamir Khan","Dangal");
                put("Amitabh Bacchan","Sholey");
            }
        };
        for (String actorName :actors){
            String [] names = actorName.split(" ");

            Date dateOfBirth = DataLoaderService.between(new Date(1960,01,01),new Date(1980,01,01));
            Date dateOfLaunch = DataLoaderService.between(new Date(1990,01,01),new Date(2000,01,01));
            Film film = new Film(films.get(actorName),dateOfLaunch);
            filmRepository.save(film);
            Actor actor = new Actor(names[0],names[1],dateOfBirth,"Mumbai India",film.getFilmId());
            actorRepository.save(actor);

        }


    }

    public static Date between(Date startInclusive, Date endExclusive) {
        long startMillis = startInclusive.getTime();
        long endMillis = endExclusive.getTime();
        long randomMillisSinceEpoch = ThreadLocalRandom
                .current()
                .nextLong(startMillis, endMillis);

        return new Date(randomMillisSinceEpoch);
    }
}
