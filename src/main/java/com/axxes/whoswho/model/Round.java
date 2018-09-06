package com.axxes.whoswho.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rounds")
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private long rightPersonId;
    @Column
    private long guessedPersonId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    private List<Person> persons;
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    public Round(long rightPersonId, long guessedPersonId, List<Person> persons) {
        this.rightPersonId = rightPersonId;
        this.guessedPersonId = guessedPersonId;
        this.persons = persons;
    }

    public Round(long rightPersonId,  List<Person> persons) {
        this.rightPersonId = rightPersonId;
        this.persons = persons;
    }

    public Round(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRightPersonId() {
        return rightPersonId;
    }

    public void setRightPersonId(long rightPersonId) {
        this.rightPersonId = rightPersonId;
    }

    public long getGuessedPersonId() {
        return guessedPersonId;
    }

    public void setGuessedPersonId(long guessedPersonId) {
        this.guessedPersonId = guessedPersonId;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public String toString() {
        return "Round{" +
                "id=" + id +
                ", rightPersonId=" + rightPersonId +
                ", guessedPersonId=" + guessedPersonId +
                ", persons=" + persons +
                '}';
    }
}
