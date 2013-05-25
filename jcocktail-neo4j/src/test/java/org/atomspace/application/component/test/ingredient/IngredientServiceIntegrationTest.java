package org.atomspace.application.component.test.ingredient;

/*
 * #%L
 * JCOCKTAIL-NEO4J :: DATA LAYER AND LOGIC:: JAR
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2013 ATOMSPACE
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;
import org.atomspace.application.component.ingredient.Ingredient;
import org.atomspace.application.component.ingredient.IngredientRepository;
import org.atomspace.application.component.ingredient.IngredientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.kernel.EmbeddedGraphDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-application-context.xml")
//@Transactional
//@DirtiesContext
public class IngredientServiceIntegrationTest {
    
    private static final Logger log = Logger.getLogger(IngredientServiceIntegrationTest.class);
    
    @Autowired
    IngredientService service;
    
    @Autowired
    public IngredientRepository repository;
    
    @Autowired
    public Neo4jTemplate template;
   
    
    //@Autowired
    EmbeddedGraphDatabase graphDatabaseService;
    
    @BeforeTransaction
    public void setupData() throws Exception {
        //NOT NEED A SETUP FOR THIS MEM_DB
    }
    

    @Test
    public void testIngredientService() {
        log.info("Instance of repository="+repository);
        log.info("Instance of service="+service);
        log.info("Instance of template="+template);
        log.info("Instance of graphDatabaseService="+graphDatabaseService);
        
        Ingredient object = new Ingredient();
        object.setName("Apfelsaft");
        
        
        log.info("FRESH NodeEntity getNodeId: "+object.getNodeId());
        log.info("FRESH NodeEntity getName: "+object.getName());
        Ingredient saved = service.store(object);
        log.info("SAVED NodeEntity getNodeId: "+saved.getNodeId());
        log.info("SAVED NodeEntity getName: "+saved.getName());

        Ingredient loaded = service.findByName(object.getName());
        assertEquals("Apfelsaft",loaded.getName());
        
        
    }

    @Test
    public void saveAnXML() throws JAXBException, IOException{
        IngredientsXML list = new IngredientsXML();
        list.getIngredient().add(new Ingredient("Orangensaft"));
        list.getIngredient().add(new Ingredient("Apfelsaft"));
        list.getIngredient().add(new Ingredient("Wodka"));
        
        JAXBContext context;
        context = JAXBContext.newInstance(IngredientsXML.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        BufferedWriter b;
        b = new BufferedWriter(new FileWriter(new File("/tmp/ingredients.xml")));
        m.marshal(list, b);
        b.flush();
        b.close();
    }
}

