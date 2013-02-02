package com.epicamble.tip.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author Ollie Edwards <oliver.s.edwards@gmail.com>
 */
@Entity
public class Player extends AbstractEntity<Long> {
    
    @ManyToOne
    private User user;
    private Race race;
    private String name;
    @ManyToOne
    private Match match;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
    
    @Override
    public String toString() {
        return "Player{" + name +": " + race + "}";
    }
}
