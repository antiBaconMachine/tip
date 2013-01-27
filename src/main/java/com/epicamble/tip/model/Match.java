package com.epicamble.tip.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author Ollie Edwards <oliver.s.edwards@gmail.com>
 */
@Entity
class Match extends AbstractEntity<Long> {
    
    @ManyToOne
    private User user;

}
