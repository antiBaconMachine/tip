package com.epicamble.tip.data;

import com.epicamble.tip.model.Race;
import com.epicamble.tip.service.RaceService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Imports race information from json.
 * 
 * Used to populate both in memory testing database and live database
 * @author Ollie Edwards <oliver.s.edwards@gmail.com>
 */
@Component
public class RaceImporter {
    
    private final static Logger logger = LoggerFactory.getLogger(RaceImporter.class);
    
    @Autowired
    private ObjectMapper objectMapper; 
    
    @Autowired
    @Qualifier("raceJsonFile")
    private File jsonFile;
    
    @Autowired
    private RaceService raceService;
    
    @PostConstruct
    public void importRaces() throws IOException {
        FileInputStream fis = new FileInputStream(jsonFile);
        Set<Race> races;
        races = objectMapper.readValue(fis, new TypeReference<Set<Race>>(){});
        logger.debug("read races {}", races);
        for (Race race : races) {
            raceService.create(race);
        }
    }
}
