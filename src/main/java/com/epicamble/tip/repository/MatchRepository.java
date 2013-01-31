/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicamble.tip.repository;

import com.epicamble.tip.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Ollie Edwards <oliver.s.edwards@gmail.com>
 */
public interface MatchRepository extends JpaRepository<Match, Long> {
    
}
