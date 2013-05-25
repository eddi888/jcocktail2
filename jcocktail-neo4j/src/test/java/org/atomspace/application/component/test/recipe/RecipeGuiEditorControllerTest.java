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

import org.apache.log4j.Logger;
import org.atomspace.application.component.ingredient.IngredientService;
import org.atomspace.application.component.recipe.Recipe;
import org.atomspace.application.component.recipe.web.RecipeGuiEditorController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-application-context.xml")
public class RecipeGuiEditorControllerTest {
    
    private static final Logger log = Logger.getLogger(RecipeGuiEditorControllerTest.class);
    
    @Autowired
    RecipeGuiEditorController controller;

    @BeforeTransaction
    public void setupData() throws Exception {
        // NOT NEED A SETUP FOR THIS MEM_DB
    }

    @Test
    public void testCreateEmptyRecipe() {
        
        controller.getItem();
        
        controller.cmdCancel(); //Item have to be NULL
        
        controller.cmdNew(); //Item have to be Fresh and ID have to be NULL
        
        controller.getMetaData().setName("007");
        controller.cmdView(); //If Item existing have to Nothing Null, if Item not existing have to be in CreateMode with ID Null and Fresh Item.
        
        controller.getMetaData().setName("007");
        controller.cmdEdit(); //If Item existing have to Nothing Null, if Item not existing have to be in CreateMode with ID Null and Fresh Item.
        //TODO CHANGE SOME
        //controller.cmdSave(); //ID have to be notNull
        
    }

}
