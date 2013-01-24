package com.epicamble.tip.model;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author Ollie Edwards <oliver.s.edwards@gmail.com>
 */
@Entity
public class Technology extends AbstractPersistable<Long> {
    
    protected String name;
    protected String description;
    
    @ManyToMany(mappedBy="startingTechnologies")
    protected Collection<Race> owningRaces;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Race> getOwningRaces() {
        return owningRaces;
    }

    public void setOwningRaces(Collection<Race> owningRaces) {
        this.owningRaces = owningRaces;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 47 * hash + (this.description != null ? this.description.hashCode() : 0);
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
        final Technology other = (Technology) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.description == null) ? (other.description != null) : !this.description.equals(other.description)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Technology{" + "name=" + name + ", description=" + description + '}';
    }
}
