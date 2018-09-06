package com.axxes.whoswho.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany(targetEntity = Round.class, fetch = FetchType.EAGER, mappedBy = "game")
    @ElementCollection(targetClass = Round.class)
    private List<Round> rounds;
    @Column
    private Integer score;
    @Column
    private LocalDateTime startTime;
    @Column
    private LocalDateTime endTime;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Game(List<Round> rounds, Integer score, LocalDateTime startTime, LocalDateTime endTime, Person person) {
        this.rounds = rounds;
        this.score = score;
        this.startTime = startTime;
        this.endTime = endTime;
        this.person = person;
    }

    public Game() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", rounds=" + rounds +
                ", score=" + score +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", person=" + person +
                '}';
    }
}
