package com.santiexcofier.afinal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TaskDetailActivity extends AppCompatActivity {

    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        // Obtener la tarea desde el intent
        task = (Task) getIntent().getSerializableExtra("task");

        // Mostrar la información de la tarea en la interfaz de usuario
        displayTaskDetails();

        // Configurar el evento de clic para el botón de eliminar tarea
        Button deleteTaskButton = findViewById(R.id.deleteTaskButton);
        deleteTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Agregar la lógica para eliminar la tarea (puedes usar el ID de la tarea)
                // Después de eliminar, puedes cerrar esta actividad o hacer lo que sea necesario
                finish();
            }
        });
    }

    // Método para mostrar la información de la tarea en la interfaz de usuario
    private void displayTaskDetails() {
        TextView taskNameTextView = findViewById(R.id.taskNameTextView);
        TextView taskDescriptionTextView = findViewById(R.id.taskDescriptionTextView);

        taskNameTextView.setText(task.getName());
        taskDescriptionTextView.setText(task.getDescription());
    }
}