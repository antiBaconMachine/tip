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
    
    
}
