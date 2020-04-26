package com.javafun.graphql.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "FILM")
public class Film {
    @Id
    @Column(name = "FILM_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer filmId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DATE_OF_LAUNCH")
    private Date dateOfLaunch;

    public Film() {
    }

    public Film(String name, Date dateOfLaunch) {
       // this.filmId = filmId;
        this.name = name;
        this.dateOfLaunch = dateOfLaunch;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmID(Integer filmId) {
        this.filmId = filmId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfLaunch() {
        return dateOfLaunch;
    }

    public void setDateOfLaunch(Date dateOfLaunch) {
        this.dateOfLaunch = dateOfLaunch;
    }
}
