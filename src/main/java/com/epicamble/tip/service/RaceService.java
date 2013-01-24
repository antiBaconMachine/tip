/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicamble.tip.service;

import com.epicamble.tip.model.Race;
import java.util.List;

/**
 *
 * @author Ollie Edwards <oliver.s.edwards@gmail.com>
 */
public interface RaceService {
    
    public Race create(Race race);
    
    public List<Race> findAll();
    
    public Race findByName(String name);
    
    public Race findById(long id);
    
    public Race update(Race race); 
    
    public void delete(long id);
    
}
