package com.epicamble.tip.model;

import java.io.Serializable;
import javax.persistence.MappedSuperclass;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * Superclass for all our entities, allows us to control json representations of 
 * stock fields in a centralised fashion
 * @author Ollie Edwards <oliver.s.edwards@gmail.com>
 */
@MappedSuperclass
public abstract class AbstractEntity<PK extends Serializable> extends AbstractPersistable<PK> {
    
    @Override
    @JsonIgnore
    public boolean isNew() {
        return super.isNew();
    }
    
}
