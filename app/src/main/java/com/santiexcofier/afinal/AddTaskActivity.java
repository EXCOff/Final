package com.santiexcofier.afinal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.santiexcofier.afinal.R;
import com.santiexcofier.afinal.Task;

import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {

    private EditText taskNameEditText;
    private EditText taskDescriptionEditText;
    private EditText taskDateEditText;

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
        // Aquí puedes agregar la lógica para guardar la tarea en Firestore
        // Puedes obtener los valores de los EditText y crear una nueva instancia de la clase Task

        String taskName = taskNameEditText.getText().toString();
        String taskDescription = taskDescriptionEditText.getText().toString();
        String taskDate = taskDateEditText.getText().toString();

        // Validación básica
        if (taskName.isEmpty() || taskDescription.isEmpty() || taskDate.isEmpty()) {
            showToast("Completa todos los campos");
            return;
        }

        // Crear una nueva instancia de la clase Task
        Task newTask = new Task(taskName, taskDescription, taskDate);

        // Agregar la lógica para guardar la tarea en Firestore
        // (Puedes usar el método addTaskToFirestore que definimos anteriormente)

        // Finalizar la actividad después de guardar la tarea
        finish();
    }

    // Método para mostrar mensajes Toast
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}