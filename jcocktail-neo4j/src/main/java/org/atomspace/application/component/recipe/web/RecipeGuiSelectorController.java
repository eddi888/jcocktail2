package org.atomspace.application.component.recipe.web;

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


import org.atomspace.application.component.recipe.Recipe;
import org.atomspace.application.component.recipe.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class RecipeGuiSelectorController {

    @Autowired
    RecipeService service;

    private Page<Recipe> page; //Bean Presentation Model

    private int pageNumber=0;
    
    public Page<Recipe> getPage() {
        return page;
    }

    public void cmdInit(){
        service.findAll(pageNumber, 10);
    }
    
    public void cmdBack(){
        pageNumber=pageNumber-1;
        if(pageNumber<0) pageNumber=0;
        service.findAll(pageNumber, 10);
    }
    
    public void cmdNext(){
        pageNumber=pageNumber+1;        
        service.findAll(pageNumber, 10);
    }
    
    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
    
    
    
}
