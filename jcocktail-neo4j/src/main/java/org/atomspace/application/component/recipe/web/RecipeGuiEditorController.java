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
import org.atomspace.application.web.WebEditorMetaData;
import org.atomspace.application.web.WebMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeGuiEditorController {

    @Autowired
    RecipeService service;

    private WebMessageListener messageListener;
    private WebEditorMetaData metaData = new WebEditorMetaData();
    
    
    private Recipe item; //Bean Presentation Model

    /**
     * Save the current item
     */
    public void cmdSave() {
        item=service.store(getItem());
    }

    /**
     * Cancel the current operation
     */
    public void cmdCancel() {
        item=null;
    }
    
    /**
     * Going in Mode New for create an item 
     */
    public void cmdNew() {
        item=new Recipe();
        getItem().setName("");
    }
    
    /**
     * Going in Mode Edit for modify an item 
     */
    public void cmdEdit() {
        item=new Recipe();
        getItem().setName("");
    }

    /**
     * Going in Mode View for look details readonly of an item 
     */
    public void cmdView() {
        item=service.getFirst();
    }

    /**
     * Get Current Model
     * @return
     */
    public Recipe getItem() {
        return item;
    }

    public WebMessageListener getMessageListener() {
	return messageListener;
    }

    public void setMessageListener(WebMessageListener messageListener) {
	this.messageListener = messageListener;
    }

    public WebEditorMetaData getMetaData() {
	return metaData;
    }

    public void setMetaData(WebEditorMetaData metaData) {
	this.metaData = metaData;
    }



   
    

   

}
