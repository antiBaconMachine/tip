package com.epicamble.tip.model;

import com.epicamble.tip.repository.TechnologyRepository;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ollie Edwards <oliver.s.edwards@gmail.com>
 */
@Component
public class TechnologyCustomCollectionEditor extends CustomCollectionEditor {
    
    private final static Logger logger = LoggerFactory.getLogger(TechnologyCustomCollectionEditor.class);
    
    @Autowired
    TechnologyRepository technologyRepository;

    public TechnologyCustomCollectionEditor() {
        super(Set.class);
    }

    @Override
    protected Object convertElement(Object element) {
        if (element instanceof Technology) {
            logger.debug("We already have a technology");
            return element;
        }
        if (element instanceof String) {
            Long id = Long.parseLong((String) element);
            Technology tech = technologyRepository.findOne(id);
            logger.debug("Retrieved terchnology {}", tech);
            return tech;
        }
        logger.warn("Failed to convert technology {}", element);
        return null;
    }
}
