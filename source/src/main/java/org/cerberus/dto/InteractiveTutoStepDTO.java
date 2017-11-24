package org.cerberus.dto;

import org.cerberus.crud.entity.InteractiveTutoStepType;

public class InteractiveTutoStepDTO {
    private String selectorJquery;
    private String text;
    private InteractiveTutoStepType type;

    public InteractiveTutoStepDTO(int id, String selectorJquery, String text, InteractiveTutoStepType type) {
        this.selectorJquery = selectorJquery;
        this.text = text;
        this.type = type;
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
