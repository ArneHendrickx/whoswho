package com.axxes.whoswho.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String rightPersonId;
    @Column
    private String guessedPersonId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    private List<Person> persons;
    @ManyToOne
    @JoinColumn(name = "game_id")
    @JsonIgnore
    private Game game;

    public Round(String rightPersonId, String guessedPersonId, List<Person> persons) {
        this.rightPersonId = rightPersonId;
        this.guessedPersonId = guessedPersonId;
        this.persons = persons;
    }

    public Round(String id, List<Person> personsInRound){
        this.rightPersonId = id;
        this.persons = personsInRound;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRightPersonId() {
        return rightPersonId;
    }

    public void setRightPersonId(String rightPersonId) {
        this.rightPersonId = rightPersonId;
    }

    public String getGuessedPersonId() {
        return guessedPersonId;
    }

    public void setGuessedPersonId(String guessedPersonId) {
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
