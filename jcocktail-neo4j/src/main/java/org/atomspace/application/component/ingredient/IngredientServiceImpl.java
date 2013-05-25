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


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.neo4j.conversion.EndResult;
import org.springframework.stereotype.Service;

@Service
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    public IngredientRepository repository;

    @Override
    public Ingredient findByName(String id) {
        return repository.findByName(id);
    }

    @Override
    public Ingredient getFirst() {
        EndResult<Ingredient> all = repository.findAll();
        for (Ingredient ingredient : all) {
            return ingredient;
        }
        return null;
    }

    @Override
    public Ingredient store(Ingredient ingredient) {
        return repository.save(ingredient);
    }
    
    @Override
    public Page<Ingredient> findAll(int page, int size){
        return repository.findAllAndSortByName(new PageRequest(0,10));
    }

    @Override
    public Page<Ingredient> searchByNameAndSortByName(String search, int page, int size){
        return repository.searchByNameAndSortByName(search, new PageRequest(0,10));
    }
    
}
