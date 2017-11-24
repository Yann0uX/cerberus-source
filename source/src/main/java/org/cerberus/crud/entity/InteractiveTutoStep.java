package org.cerberus.crud.entity;

public class InteractiveTutoStep {
    private int id;
    private String selectorJquery;
    private String text;
    private InteractiveTutoStepType type;

    public InteractiveTutoStep(int id, String selectorJquery, String text, InteractiveTutoStepType type) {
        this.id = id;
        this.selectorJquery = selectorJquery;
        this.text = text;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSelectorJquery() {
        return selectorJquery;
    }

    public void setSelectorJquery(String selectorJquery) {
        this.selectorJquery = selectorJquery;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public InteractiveTutoStepType getType() {
        return type;
    }

    public void setType(InteractiveTutoStepType type) {
        this.type = type;
    }
}
