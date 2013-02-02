package com.epicamble.tip.data;

import com.epicamble.tip.model.Race;
import com.epicamble.tip.model.Technology;
import com.epicamble.tip.repository.TechnologyRepository;
import com.epicamble.tip.service.RaceService;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
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
    public void doImportOnStartup() throws IOException, InterruptedException {
        logger.info("Checking whether to import data from json: {}", doImportOnStartup);
        if (doImportOnStartup) {
            importAll();
        }
    }

    public void importAll() throws IOException, InterruptedException {
        importTechnology();
        importRaces();
    }

    public void importTechnology() throws IOException {
        logger.info("Importing technologies from JSON");
        Set<Technology> technologies = getTechnologyFromJSON();
        for (Technology t : technologies) {
            Technology exisitingTech = technologyRepository.findByName(t.getName());
            if (exisitingTech == null) {
                technologyRepository.save(t);
            }
        }
    }

    public void importRaces() throws IOException, InterruptedException {
        logger.info("Importing races from JSON");
        List<Race> races = getRacesFromJSON();
        for (Race race : races) {
            logger.debug("Iterating race {}", race);
            Race existingRace = raceService.findByName(race.getName());
            if (existingRace != null) {
                logger.debug("found exisiting race with same name race {}", existingRace);
                raceService.delete(existingRace.getId());
            }
            Set<Technology> techs = new HashSet<Technology>();
            for (Technology t : race.getStartingTechnologies()) {
                logger.debug("Iterating technology {}", t);
                Technology exisitingTech = technologyRepository.findByName(t.getName());
                if (exisitingTech != null) {
                    logger.debug("Found exisiting tech {}", exisitingTech);
                    if (!exisitingTech.getOwningRaces().contains(race)) {
                        logger.debug("adding new owning race ");
                        exisitingTech.getOwningRaces().add(race);
                    }
                    techs.add(exisitingTech);
                } else {
                    logger.warn("No technology found for {}", t);
                    techs.add(t);
                }
            }
            race.setStartingTechnologies(null);
            logger.debug("creating new race {}", race);
            raceService.create(race);
            /**
             * If we try and create the entity with existing techs we get
             * detached entity exception so instead we do in two steps
             *
             * TODO: prob a better way to do this
             */
            race.setStartingTechnologies(techs);
            logger.debug("updating race {}", race);
            raceService.update(race);
            logger.debug("completed iteration {}", race);
        }
    }

    public Set<Technology> getTechnologyFromJSON() throws IOException {
        FileInputStream fis = new FileInputStream(technologiesJson.getFile());
        Set<Technology> technologies;
        technologies = objectMapper.readValue(fis, new TypeReference<Set<Technology>>() {
        });
        logger.info("read technologies {}", technologies);
        return technologies;
    }

    public List<Race> getRacesFromJSON() throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(racesJson.getFile());
        List<Race> races;
        races = objectMapper.readValue(fis, new TypeReference<List<Race>>() {
        });
        //logger.debug("read races {}", races);
        logger.info("read races");
        return races;
    }
}
