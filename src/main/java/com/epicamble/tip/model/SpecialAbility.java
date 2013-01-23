package com.epicamble.tip.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author Ollie Edwards <oliver.s.edwards@gmail.com>
 */
@Entity
public class SpecialAbility extends AbstractPersistable<Long> {
    
    protected String description;
    @ManyToOne
    protected Race owningRace;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Race getOwningRace() {
        return owningRace;
    }

    public void setOwningRace(Race owningRace) {
        this.owningRace = owningRace;
    }
}
