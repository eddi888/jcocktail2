package org.atomspace.application.component.ingredient.web;

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


import org.atomspace.application.component.ingredient.Ingredient;
import org.atomspace.application.component.ingredient.IngredientService;
import org.atomspace.application.web.WebSelectorMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class IngredientSelectorController {

    @Autowired
    private IngredientService service;
    
    private WebSelectorMetaData metaData;

    private Page<Ingredient> page; //Bean Presentation Model
    
    public Page<Ingredient> getPage() {
        return page;
    }

    public void cmdList(int pageNumber){
        page = service.findAll(pageNumber, 10);
    }
    
    public void cmdBack(){
        
        if(page.getNumber()<0){
        	page = service.findAll(0, 10);
        }else{
        	page = service.findAll(page.getNumber()-1, 10);
        }
        
    }
    
    public void cmdNext(){
        page = service.findAll(page.getNumber()+1, 10);
    }
    
    
	public WebSelectorMetaData getMetaData() {
		return metaData;
	}

	public void setMetaData(WebSelectorMetaData metaData) {
		this.metaData = metaData;
	}
    
   
    
}
