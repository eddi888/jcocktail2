package org.atomspace.application.component.navigation.gui;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.ui.HorizontalLayout;


@Component
@Scope("session")
@SuppressWarnings("serial")
public class NavigationUI extends HorizontalLayout {
   
	public NavigationUI() {
		this.addComponent(new NaviLink("Home", "./images/navi_home.png", "#/welcome/list/0"));
		this.addComponent(new NaviLink("Rezepte", "./images/navi_recipe.png", "#/recipe/list/0"));
		this.addComponent(new NaviLink("Zutaten", "./images/navi_ingredient.png", "#/ingredient/list/0"));
		this.addComponent(new NaviLink("Potential", "./images/navi_potential.png", "#/potential/list/0"));
		this.addComponent(new NaviLink("Einkaufszettel", "./images/navi_shopinglist.png", "#/shopinglist/list/0"));
		this.addComponent(new NaviLink("Barbestand", "./images/navi_inventory.png", "#/inventory/list/0"));
		this.addComponent(new NaviLink("Webuser", "./images/navi_webuser.png", "#/webuser/list/0"));
	}

}
