package com.epicamble.tip.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
    @JoinColumn(name="owningrace_id")
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + (this.description != null ? this.description.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SpecialAbility other = (SpecialAbility) obj;
        if ((this.description == null) ? (other.description != null) : !this.description.equals(other.description)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SpecialAbility{" + "description=" + description + '}';
    }
}
