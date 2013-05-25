package org.atomspace.application.component.welcome.gui;

import org.atomspace.application.gui.WebCommand;
import org.atomspace.application.gui.WebComponent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;


@Component
@Scope("session")
@SuppressWarnings("serial")
public class WelcomeUI extends Panel implements WebComponent{

	private boolean init = false;
	private Label welcome;
	
	public WelcomeUI() {
		
	}
	
    public WelcomeUI(String localizedMessage) {
		if(welcome!=null){
			welcome.setValue(localizedMessage);
		}
	}

	@Override
    public void init(WebCommand command) {
		if(init==false){
			welcome = new Label("WELCOME!!!");
			this.setContent(welcome);
		}
        
        
    }

   

}
