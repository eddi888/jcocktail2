package org.atomspace.application.component.ingredient;

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



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;

public interface IngredientRepository extends GraphRepository<Ingredient>, RelationshipOperationsRepository<Ingredient> {

    Ingredient findByName(String id);

    @Query("START n=node(*) WHERE n.__type__! = 'org.atomspace.application.component.ingredient.Ingredient' RETURN n ORDER BY n.name ASC")
    Page<Ingredient> findAllAndSortByName(Pageable pageable);
    
    @Query("START n=node(*) WHERE n.__type__! = 'org.atomspace.application.component.ingredient.Ingredient' AND n.name! =~ '{0}.*' RETURN n ORDER BY n.name ASC")
    Page<Ingredient> searchByNameAndSortByName(String search, Pageable pageable);
    
}
