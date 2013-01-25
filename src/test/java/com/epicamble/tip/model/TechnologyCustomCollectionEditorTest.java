/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicamble.tip.model;

import com.epicamble.tip.repository.TechnologyRepository;
import com.epicamble.tip.test.AbstractTest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Ollie Edwards <oliver.s.edwards@gmail.com>
 */
public class TechnologyCustomCollectionEditorTest extends AbstractTest {
    
    private static final Logger logger = LoggerFactory.getLogger(TechnologyCustomCollectionEditorTest.class);
    
    @Autowired
    private TechnologyCustomCollectionEditor instance;
    @Autowired
    private TechnologyRepository technologyRepository;
    
    public TechnologyCustomCollectionEditorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of convertElement method, of class TechnologyCustomCollectionEditor.
     */
    @Test
    public void testConvertElement() throws FileNotFoundException, IOException {
        System.out.println("convertElement");
        Technology expResult = technologyRepository.findAll().iterator().next();
        String id = String.valueOf(expResult.getId());
        Object result = instance.convertElement(id);
        assertEquals(expResult, result);
    }
}
