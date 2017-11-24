package org.cerberus.dto;

import java.util.List;

public class InteractiveTutoDTO {

    private int id;
    private String libelle;

    private List<InteractiveTutoStepDTO> step;

    public InteractiveTutoDTO() {
    }

    public InteractiveTutoDTO(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
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

    public List<InteractiveTutoStepDTO> getSteps() {
        return step;
    }

    public void setStep(List<InteractiveTutoStepDTO> step) {
        this.step = step;
    }
}
