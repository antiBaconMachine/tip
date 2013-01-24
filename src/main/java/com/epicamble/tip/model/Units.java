package com.epicamble.tip.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author Ollie Edwards <oliver.s.edwards@gmail.com>
 */
@Entity
public class Units extends AbstractPersistable<Long> {
    
    public enum UNIT_TYPE {
        SPACE_DOCK,
        CARRIER,
        GROUND_FORCE,
        DREADNOUGHT,
        FIGHTER,
        PDS
    }
    
    @Enumerated(EnumType.STRING) 
    protected UNIT_TYPE type;
    protected Integer count;
    @ManyToOne(cascade = CascadeType.ALL)
    protected Race owningRace;

    public UNIT_TYPE getType() {
        return type;
    }

    public void setType(UNIT_TYPE type) {
        this.type = type;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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
        hash = 41 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 41 * hash + (this.count != null ? this.count.hashCode() : 0);
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
        final Units other = (Units) obj;
        if (this.type != other.type) {
            return false;
        }
        if (this.count != other.count && (this.count == null || !this.count.equals(other.count))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Units{" + "type=" + type + ", count=" + count + '}';
    }
    
}
