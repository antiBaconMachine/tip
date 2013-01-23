package com.epicamble.tip.model;

import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author Ollie Edwards <oliver.s.edwards@gmail.com>
 */
@Entity
public class Race extends AbstractPersistable<Long> {
    
    protected String name;
    protected String description;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy="owningRace")
    protected Collection<Units> startingUnits;
    
    @ManyToMany
    protected Collection<Technology> startingTechnologies;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy="owningRace")
    protected Collection<SpecialAbility> specialAbilities;

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

    public Collection<Units> getStartingUnits() {
        return startingUnits;
    }

    public void setStartingUnits(Collection<Units> startingUnits) {
        this.startingUnits = startingUnits;
    }

    public Collection<Technology> getStartingTechnologies() {
        return startingTechnologies;
    }

    public void setStartingTechnologies(Collection<Technology> startingTechnologies) {
        this.startingTechnologies = startingTechnologies;
    }

    public Collection<SpecialAbility> getSpecialAbilities() {
        return specialAbilities;
    }

    public void setSpecialAbilities(Collection<SpecialAbility> specialAbilities) {
        this.specialAbilities = specialAbilities;
    }
    
}
