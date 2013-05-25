package org.atomspace.application.gui;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Hashtable;
import java.util.Map;

import org.atomspace.application.component.navigation.gui.NavigationUI;
import org.atomspace.application.component.welcome.gui.WelcomeUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.server.Page.UriFragmentChangedEvent;
import com.vaadin.server.Page.UriFragmentChangedListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;



@Component
@Scope("prototype")
@SuppressWarnings("serial")
public class WebUI extends UI {
    
    @Autowired
    private transient ApplicationContext applicationContext;
    
    private HorizontalLayout main = null;
    
    void commandNavigator(String frag) {
        try{
            WebCommand command = new WebCommand(frag);
            if(command.getCollection()!=null && command.getAction()!=null){
                String view = views.get(command.getCollection()+"ui");
                if(view==null) throw new Exception("THE COLLECTION '"+command.getCollection()+"' NOT EXIST");
                WebComponent pmtComponent = applicationContext.getBean(views.get(command.getCollection()+"ui"), WebComponent.class);
                main.removeAllComponents();
                main.addComponent(pmtComponent);
                pmtComponent.init(command);
                
            }else{
                main.removeAllComponents();
                main.addComponent(new WelcomeUI("--- NO Collection && NO Action ---"));
            }
        }catch (Exception e) {
            main.removeAllComponents();
            main.addComponent(new WelcomeUI(e.getLocalizedMessage()));
            StringWriter y = new StringWriter();
            PrintWriter stringWriter = new PrintWriter(y);
            e.printStackTrace(stringWriter);
            System.out.println(y);
            //TextArea errorArea = new TextArea("ExceptionStackTrace");
            //main.addComponent(errorArea);
            //errorArea.setParent(main);
        }
    }

    @Override
    public void init(VaadinRequest request) {

        this.setWidth("100%");
    	VerticalLayout page = new VerticalLayout();
    	page.setWidth("100%");
    	page.setHeight("400px");
    	
    	VerticalLayout bodyLeft = new VerticalLayout();
    	bodyLeft.setWidth("5px");
    	bodyLeft.addComponent(new Label("L"));
    	
        VerticalLayout bodyRight = new VerticalLayout();
        bodyRight.addComponent(new Label("R"));
        bodyRight.setWidth("5px");
        
        VerticalLayout body = new VerticalLayout();
        body.setWidth("600px");
        main = new HorizontalLayout();
        
        // topbar (navigation)
        //HorizontalLayout navi = new HorizontalLayout();
        //body.addComponent(navi);
        
        NavigationUI navi= new NavigationUI();
        body.addComponent(navi);
        navi.setHeight("144px");
        navi.setWidth("600px");
        body.setComponentAlignment(navi, Alignment.TOP_CENTER);
        
        //page.addComponent(bodyLeft);
        page.addComponent(body);
        //page.addComponent(bodyRight);
        //page.setComponentAlignment(bodyLeft, Alignment.MIDDLE_RIGHT);
        page.setComponentAlignment(body, Alignment.TOP_CENTER);
        //page.setComponentAlignment(bodyRight, Alignment.MIDDLE_LEFT);
        this.setContent(page);
        
        
        body.addComponent(main);
        
        
        String[] beans = applicationContext.getBeanDefinitionNames();
        for (String bean : beans) {
            views.put(bean.toLowerCase(), bean);
        }
        
        getPage().addUriFragmentChangedListener(new UriFragmentChangedListener() {
            @Override
            public void uriFragmentChanged(UriFragmentChangedEvent event) {
                commandNavigator(event.getUriFragment());
            }
        });
        commandNavigator(getPage().getUriFragment());
    }
    
    Map<String, String> views = new Hashtable<String, String>();
}
