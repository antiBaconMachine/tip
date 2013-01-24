package com.epicamble.tip.service.impl;

import com.epicamble.tip.model.Race;
import com.epicamble.tip.repository.RaceRepository;
import com.epicamble.tip.service.RaceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ollie Edwards <oliver.s.edwards@gmail.com>
 */
@Component
public class RaceServiceImpl implements RaceService {
    
    @Autowired
    private RaceRepository raceRepository;

    @Override
    public Race create(Race race) {
        return raceRepository.save(race);
    }

    @Override
    public List<Race> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Race findById(long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Race update(Race race) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Race findByName(String name) {
        return raceRepository.findByName(name);
    }
    
    

}
