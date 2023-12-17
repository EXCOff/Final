package com.santiexcofier.afinal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddTaskActivity extends AppCompatActivity {

    private EditText taskNameEditText;
    private EditText taskDescriptionEditText;
    private EditText taskDateEditText;

    // Referencia a la colección "tareas" en Firestore
    private CollectionReference tasksRef = FirebaseFirestore.getInstance().collection("tareas");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        // Obtener referencias de vistas
        taskNameEditText = findViewById(R.id.taskNameEditText);
        taskDescriptionEditText = findViewById(R.id.taskDescriptionEditText);
        taskDateEditText = findViewById(R.id.taskDateEditText);

        Button saveTaskButton = findViewById(R.id.saveTaskButton);

        // Configurar evento de clic para el botón de guardar tarea
        saveTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTask();
            }
        });
    }

    private void saveTask() {
        // Obtener los valores de los EditText
        String taskName = taskNameEditText.getText().toString();
        String taskDescription = taskDescriptionEditText.getText().toString();
        String taskDate = taskDateEditText.getText().toString();

        // Validación básica
        if (taskName.isEmpty() || taskDescription.isEmpty() || taskDate.isEmpty()) {
            showToast("Completa todos los campos");
            return;
        }

        // Crear una nueva instancia de la clase Task
        Task newTask = createTask(taskName, taskDescription, taskDate);

        // Agregar la tarea a Firestore
        addTaskToFirestore(newTask);

        // Finalizar la actividad después de guardar la tarea
        finish();
    }

    // Método para crear una instancia de la clase Task
    private Task createTask(String name, String description, String date) {
        // Utiliza el constructor correspondiente en la clase Task
        return new Task(name, description, date);
    }

    // Método para agregar la tarea a Firestore
    private void addTaskToFirestore(Task task) {
        // Agregar la tarea a Firestore
        tasksRef.add(task)
                .addOnSuccessListener(documentReference -> {
                    showToast("Tarea guardada exitosamente");
                })
                .addOnFailureListener(e -> {
                    showToast("Error al guardar la tarea");
                });
    }

    // Método para mostrar mensajes Toast
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}