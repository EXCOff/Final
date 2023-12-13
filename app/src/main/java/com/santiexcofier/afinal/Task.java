package com.santiexcofier.afinal;

public class Task {

    private String description;

    // Constructor
    public Task(String description) {
        this.description = description;
    }

    // Método para obtener la descripción de la tarea
    public String getDescription() {
        return description;
    }

    // Método para establecer la descripción de la tarea
    public void setDescription(String description) {
        this.description = description;
    }
}