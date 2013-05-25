package org.atomspace.application.component.test.recipe;

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
import static org.junit.Assert.assertNotNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;
import org.atomspace.application.component.ingredient.Ingredient;
import org.atomspace.application.component.ingredient.IngredientService;
import org.atomspace.application.component.recipe.Recipe;
import org.atomspace.application.component.recipe.RecipeRow;
import org.atomspace.application.component.recipe.RecipeService;
import org.atomspace.application.component.test.ingredient.IngredientsXML;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-application-context.xml")
public class RecipeServiceIntegrationTest {

    private static final Logger log = Logger.getLogger(RecipeServiceIntegrationTest.class);

    @Autowired
    RecipeService service;

    @Autowired
    IngredientService ingredService;

    @BeforeTransaction
    public void setupData() throws Exception {
        // NOT NEED A SETUP FOR THIS MEM_DB
    }

    @Test
    public void testCreateEmptyRecipe() {

        Recipe recipe = new Recipe();
        recipe.setName("NixMix");
        Recipe saved = service.store(recipe);
        log.info("SAVED getNodeId: " + saved.getNodeId());

        Recipe loaded = service.findByName(saved.getName());

        assertEquals("NixMix", loaded.getName());

    }

    @Test
    public void testCreateFullRecipe() {
        // ADD INGREDIENTS
        Ingredient orangensaft = new Ingredient("Orangensaft");
        orangensaft = ingredService.store(orangensaft);
        Ingredient wodka = new Ingredient("Wodka");
        wodka = ingredService.store(wodka);

        // ADD RECIPE WITH IngredientSpecification
        Recipe recipe = new Recipe();
        recipe.setName("Screwdriver");
        recipe.getRows().add(new RecipeRow(0, recipe, orangensaft, new BigDecimal("100")));
        recipe.getRows().add(new RecipeRow(1, recipe, wodka, new BigDecimal("50")));
        Collection<RecipeRow> y = recipe.getRows();
        for (RecipeRow ingredientSpecification : y) {
            //log.info("--" + ingredientSpecification.getNodeId());
            log.info("--" + ingredientSpecification.getQuantity());
            log.info("--" + ingredientSpecification.getIngredient());
        }
        Recipe saved = service.store(recipe);
        log.info("SAVED getNodeId: " + saved.getNodeId());

        Recipe loaded = service.findByName("Screwdriver");
        loaded.getNodeId();
        loaded.getName();
        Collection<RecipeRow> rows = loaded.getRows();
        for (RecipeRow row : rows) {
            //log.info("--" + row.getNodeId());
            //assertNotNull(row.getNodeId());
            log.info("--" + row.getQuantity());
            assertNotNull(row.getQuantity());
            log.info("--" + row.getIngredient());
            assertNotNull(row.getIngredient());
        }

    }

    @Test
    public void testFindAllRecipes() {
        // ADD INGREDIENTS
        Ingredient orangensaft = new Ingredient("Orangensaft");
        orangensaft = ingredService.store(orangensaft);
        Ingredient wodka = new Ingredient("Wodka");
        wodka = ingredService.store(wodka);
        Ingredient vermouthRosso = new Ingredient("Vermouth Rosso");
        wodka = ingredService.store(vermouthRosso);

        Recipe recipe = new Recipe();
        recipe.setName("007");
        recipe.getRows().add(new RecipeRow(0, recipe, orangensaft, new BigDecimal("5")));
        recipe.getRows().add(new RecipeRow(1, recipe, vermouthRosso, new BigDecimal("30")));
        recipe.getRows().add(new RecipeRow(2, recipe, wodka, new BigDecimal("30")));
        service.store(recipe);

        log.info("---------------");
        Page<Recipe> recipes = service.findAll(0, 10);
        log.info("---------------");
        assertNotNull(recipes);
        assertNotNull(recipes.getContent());
        assertNotNull(recipes.getContent().get(0));
        assertNotNull(recipes.getContent().get(0).getName());
        assertEquals("007", recipes.getContent().get(0).getName());
        assertNotNull(recipes.getContent().get(0).getRows());

        Collection<RecipeRow> rows = recipes.getContent().get(0).getRows();
        assertNotNull(rows);
        for (RecipeRow row : rows) {
            assertNotNull(row.getIngredient());
            assertNotNull(row.getQuantity());
        }

        assertEquals(3,service.searchByNameAndSortByName("0", 0, 10).getContent().size());;
        
        
    }

    @Test
    public void saveAnXML() throws JAXBException, IOException{
        RecipesXML list = new RecipesXML();
        Recipe recipe = new Recipe();
        recipe.setName("C1");
        recipe.getRows().add(new RecipeRow(1, recipe, new Ingredient("apfelsaft"), new BigDecimal("99")));
        list.getRecipe().add(recipe);
        
        JAXBContext context;
        context = JAXBContext.newInstance(RecipesXML.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        BufferedWriter b;
        b = new BufferedWriter(new FileWriter(new File("/tmp/recipes.xml")));
        m.marshal(list, b);
        b.flush();
        b.close();
    }
    
}
