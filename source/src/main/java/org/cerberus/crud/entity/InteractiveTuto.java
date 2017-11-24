package org.cerberus.crud.entity;

import java.util.List;

public class InteractiveTuto {

    private int id;
    private String libelle;

    private List<InteractiveTutoStep> step;

    public InteractiveTuto() {
    }

    public InteractiveTuto(int id, String libelle, List<InteractiveTutoStep> step) {
        this.id = id;
        this.libelle = libelle;
        this.step = step;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<InteractiveTutoStep> getSteps() {
        return step;
    }

    public void setStep(List<InteractiveTutoStep> step) {
        this.step = step;
    }
}
