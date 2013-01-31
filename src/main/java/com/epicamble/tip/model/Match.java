package com.epicamble.tip.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 * Entity represents a TI Match.
 * 
 * Contains game setup info and a list of players;
 * @author Ollie Edwards <oliver.s.edwards@gmail.com>
 */
@Entity
public class Match extends AbstractEntity<Long> {
    
    private User owner;
    @OneToMany(mappedBy="match")
    private List<Player> players;
    private String name;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date created = new Date();
    
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

}
