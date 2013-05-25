package org.atomspace.application.component.recipe;

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


import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlTransient;

import org.atomspace.application.component.ingredient.Ingredient;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;
import org.springframework.data.neo4j.support.index.IndexType;


@RelationshipEntity(type = "INGREDIENT_IN_RECIPE")
public class RecipeRow {

    @GraphId
    private Long id;

    @StartNode
    private Recipe recipe;

    private int rowId;

    @EndNode
    private Ingredient ingredient;

    private BigDecimal quantity;

    public RecipeRow() {

    }

    public RecipeRow(int rowId, Recipe recipe, Ingredient ingredient,
	    BigDecimal quantity) {

	super();
	this.recipe = recipe;
	this.rowId = rowId;
	this.ingredient = ingredient;
	this.quantity = quantity;
    }

    @XmlTransient
    public Recipe getRecipe() {
	return recipe;
    }

    
    public void setRecipe(Recipe recipe) {
	this.recipe = recipe;
    }

    public int getRowId() {
	return rowId;
    }

    public void setRowId(int rowId) {
	this.rowId = rowId;
    }

    public Ingredient getIngredient() {
	return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
	this.ingredient = ingredient;
    }

    public BigDecimal getQuantity() {
	return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
	this.quantity = quantity;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    
}
