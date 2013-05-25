package org.atomspace.application.gui;

public class WebCommand {
    private String collection;  // COLLECTION
    private Action action;      // CRUD
    private String parameter;   // view id,search string,list page nr

    public WebCommand(String frag) {
        if (frag != null && frag.contains("/") && frag.split("/").length >= 4) {
            String[] parts = frag.split("/");
            collection = parts[1].toLowerCase();
            action = Action.fromValueAction(parts[2].toLowerCase());
            parameter = parts[3].toLowerCase();
        } else if (frag != null && frag.contains("/") && frag.split("/").length >= 3) {
            String[] parts = frag.split("/");
            collection = parts[1].toLowerCase();
            action = Action.fromValueAction(parts[2].toLowerCase());
            parameter = null;
        } else if (frag != null && frag.contains("/") && frag.split("/").length >= 2) {
            String[] parts = frag.split("/");
            collection = parts[1].toLowerCase();
            action = null;
            parameter = null;
        } else {
            collection = null;
            action = null;
            parameter = null;
        }
    }

    public WebCommand(String collection, Action action, String parameter) {
        super();
        this.collection = collection;
        this.action = action;
        this.parameter = parameter;
    }

    public String getCollection() {
        return collection;
    }

    public Action getAction() {
        return action;
    }

    public String getParameter() {
        return parameter;
    }

    @Override
    public String toString() {
        return "/" + collection + "/" + action + "/" + parameter;
    }

    public enum Action {

        NEW ("new"), VIEW ("view"), EDIT ("edit"), LIST ("list"), COPY ("copy"), PRINT ("print"), DONE ("done"), REMOVE ("remove");

        private String action;

        private Action(String action) {
            this.action = action;
        }

        public String getAction() {
            return action;
        }

        public static Action fromValueAction(String v) {
            for (Action c: Action.values()) {
                if (c.action.equals(v)) {
                    return c;
                }
            }
            throw new IllegalArgumentException("THE ACTION '"+v+"' NOT EXIST");
        }
    }

}
