package com.santiexcofier.afinal;
import java.io.Serializable;
public class Task implements Serializable {

    private String id;
    private String name;
    private String description;
    private String date;

    // Constructor con id
    public Task(String id, String name, String description, String date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
    }

    // Constructor sin id
    public Task(String name, String description, String date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public Task(String description, String date) {
        this.description = description;
        this.date = date;
    }



    // Método para obtener el ID de la tarea
    public String getId() {
        return id;
    }

    // Método para establecer el ID de la tarea
    public void setId(String id) {
        this.id = id;
    }

    // Método para obtener el nombre de la tarea
    public String getName() {
        return name;
    }

    // Método para establecer el nombre de la tarea
    public void setName(String name) {
        this.name = name;
    }

    // Método para obtener la descripción de la tarea
    public String getDescription() {
        return description;
    }

    // Método para establecer la descripción de la tarea
    public void setDescription(String description) {
        this.description = description;
    }

    // Método para obtener la fecha de la tarea
    public String getDate() {
        return date;
    }

    // Método para establecer la fecha de la tarea
    public void setDate(String date) {
        this.date = date;
    }
}