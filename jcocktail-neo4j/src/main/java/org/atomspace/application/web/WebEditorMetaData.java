package org.atomspace.application.web;

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


public class WebEditorMetaData {

    public enum Mode {
        EDIT, NEW, VIEW, EMPTY
    }

    private Mode mode = Mode.EMPTY;

    private String name;

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

  
}