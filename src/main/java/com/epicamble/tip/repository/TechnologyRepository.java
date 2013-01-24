package com.epicamble.tip.repository;

import com.epicamble.tip.model.Race;
import com.epicamble.tip.model.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Ollie Edwards <oliver.s.edwards@gmail.com>
 */
public interface TechnologyRepository extends JpaRepository<Technology, Long> {
    
    public Race findByName(String name);
    
}
