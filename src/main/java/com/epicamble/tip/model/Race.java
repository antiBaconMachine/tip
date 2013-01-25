package com.epicamble.tip.model;

import com.epicamble.tip.util.UNIT_TYPE;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.util.AutoPopulatingList;

/**
 *
 * @author Ollie Edwards <oliver.s.edwards@gmail.com>
 */
@Entity
public class Race extends AbstractPersistable<Long> {
    
    protected String name;
    protected String description;
    
    @ElementCollection
    protected Map<UNIT_TYPE, Integer> startingUnits;
    
    @ManyToMany(cascade = CascadeType.ALL)
    protected Set<Technology> startingTechnologies;
    
    //http://docs.jboss.org/hibernate/stable/annotations/reference/en/html/entity.html#entity-mapping-association-collections 2.2.5.3.1.1. 
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="owningrace_id")
    protected List<SpecialAbility> specialAbilities = new AutoPopulatingList<SpecialAbility>(SpecialAbility.class);

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

    public Map<UNIT_TYPE, Integer> getStartingUnits() {
        return startingUnits;
    }

    public void setStartingUnits(Map<UNIT_TYPE, Integer> startingUnits) {
        this.startingUnits = startingUnits;
    }

    public Set<Technology> getStartingTechnologies() {
        return startingTechnologies;
    }

    public void setStartingTechnologies(Set<Technology> startingTechnologies) {
        this.startingTechnologies = startingTechnologies;
    }

    public List<SpecialAbility> getSpecialAbilities() {
        return specialAbilities;
    }

    public void setSpecialAbilities(List<SpecialAbility> specialAbilities) {
        this.specialAbilities = specialAbilities;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 13 * hash + (this.description != null ? this.description.hashCode() : 0);
        hash = 13 * hash + (this.startingUnits != null ? this.startingUnits.hashCode() : 0);
        hash = 13 * hash + (this.startingTechnologies != null ? this.startingTechnologies.hashCode() : 0);
        hash = 13 * hash + (this.specialAbilities != null ? this.specialAbilities.hashCode() : 0);
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
        final Race other = (Race) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.description == null) ? (other.description != null) : !this.description.equals(other.description)) {
            return false;
        }
        if (this.startingUnits != other.startingUnits && (this.startingUnits == null || !this.startingUnits.equals(other.startingUnits))) {
            return false;
        }
        if (this.startingTechnologies != other.startingTechnologies && (this.startingTechnologies == null || !this.startingTechnologies.equals(other.startingTechnologies))) {
            return false;
        }
        if (this.specialAbilities != other.specialAbilities && (this.specialAbilities == null || !this.specialAbilities.equals(other.specialAbilities))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Race{" + "name=" + name + 
                ", description=" + description + 
                ", startingUnits=" + startingUnits + 
                ", startingTechnologies=" + startingTechnologies + 
                ", specialAbilities=" + specialAbilities + '}';
    }
    
    
}
