package com.epicamble.tip.data;

import com.epicamble.tip.model.Race;
import com.epicamble.tip.model.Technology;
import com.epicamble.tip.repository.TechnologyRepository;
import com.epicamble.tip.service.RaceService;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * Imports seed data from json.
 *
 * Used to populate both in memory testing database and live database
 *
 * @author Ollie Edwards <oliver.s.edwards@gmail.com>
 */
@Component
public class DataImporter {

    private final static Logger logger = LoggerFactory.getLogger(DataImporter.class);
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private Resource racesJson;
    @Autowired
    private Resource technologiesJson;
    @Autowired
    private RaceService raceService;
    @Autowired
    private TechnologyRepository technologyRepository;
    @Value("${doImportOnStartup}")
    protected boolean doImportOnStartup;

    @PostConstruct
    public void doImportOnStartup() throws IOException {
        logger.debug("Checking whether to import data from json: {}", doImportOnStartup);
        if (doImportOnStartup) {
            importAll();
        }
    }
    
    public void importAll() throws IOException {
        importTechnology();
        importRaces();
    }

    public void importTechnology() throws IOException {
        logger.debug("Importing technologies from JSON");
        Set<Technology> technologies = getTechnologyFromJSON();
        for (Technology t : technologies) {
            Technology exisitingTech = technologyRepository.findByName(t.getName());
            if (exisitingTech == null) {
                technologyRepository.save(t);
            }
        }
    }

    public void importRaces() throws IOException {
        logger.debug("Importing races from JSON");
        Set<Race> races = getRacesFromJSON();
        for (Race race : races) {
            Race existingRace = raceService.findByName(race.getName());
            if (existingRace != null) {
                raceService.delete(existingRace.getId());
            }
            Set<Technology> techs = new HashSet<Technology>();
            for (Technology t : race.getStartingTechnologies()) {
                Technology exisitingTech = technologyRepository.findByName(t.getName());
                if (exisitingTech != null) {
                    if (!exisitingTech.getOwningRaces().contains(race)) {
                        exisitingTech.getOwningRaces().add(race);
                    }
                    techs.add(exisitingTech);
                } else {
                    logger.warn("No technology found for {}", t);
                    techs.add(t);
                }
            }
            race.setStartingTechnologies(techs);
            raceService.create(race);
        }
    }

    public Set<Technology> getTechnologyFromJSON() throws IOException {
        FileInputStream fis = new FileInputStream(technologiesJson.getFile());
        Set<Technology> technologies;
        technologies = objectMapper.readValue(fis, new TypeReference<Set<Technology>>() {
        });
        logger.debug("read technologies {}", technologies);
        return technologies;
    }

    public Set<Race> getRacesFromJSON() throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(racesJson.getFile());
        Set<Race> races;
        races = objectMapper.readValue(fis, new TypeReference<Set<Race>>() {
        });
        logger.debug("read races {}", races);
        return races;
    }
}
