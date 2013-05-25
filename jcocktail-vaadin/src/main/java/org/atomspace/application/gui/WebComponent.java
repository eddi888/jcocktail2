package org.atomspace.application.gui;

import com.vaadin.ui.Component;

public interface WebComponent extends Component {
    void init(WebCommand command);
}
