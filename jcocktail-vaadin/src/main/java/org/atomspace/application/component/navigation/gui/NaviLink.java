package org.atomspace.application.component.navigation.gui;

import com.vaadin.server.ExternalResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Link;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class NaviLink extends VerticalLayout {
	
	public NaviLink(String text, String image, String link) {
	    Link link1 = new Link();
	    link1.setResource(new ExternalResource(link ));
	    link1.setIcon( new ThemeResource(image));
	    this.addComponent(link1);
	    Link link1text =  new Link(text,new ExternalResource(link));
	    this.addComponent(link1text);
	    this.setComponentAlignment(link1, Alignment.BOTTOM_CENTER);
	    this.setComponentAlignment(link1text, Alignment.BOTTOM_CENTER);
	}
}
