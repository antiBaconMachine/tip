package com.epicamble.tip.repository;

import com.epicamble.tip.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Ollie Edwards <oliver.s.edwards@gmail.com>
 */
public interface PlayerRepository extends JpaRepository<Player, Long> {
    
}
