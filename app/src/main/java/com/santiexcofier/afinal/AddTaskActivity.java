package com.santiexcofier.afinal;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddTaskActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private EditText taskDescriptionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        // Inicializar Firestore
        db = FirebaseFirestore.getInstance();

        // Referenciar vistas desde el diseño
        taskDescriptionEditText = findViewById(R.id.editTextTaskDescription);
        Button addTaskButton = findViewById(R.id.addTaskButton);

        // Manejar clic en el botón de agregar tarea
        addTaskButton.setOnClickListener(view -> {
            // Obtener la descripción de la tarea ingresada por el usuario
            String taskDescription = taskDescriptionEditText.getText().toString();

            // Verificar que la descripción no esté vacía
            if (!taskDescription.isEmpty()) {
                // Agregar la tarea a Firestore
                addTaskToFirestore(taskDescription);
            } else {
                // Mostrar un mensaje de error si la descripción está vacía
                Toast.makeText(AddTaskActivity.this, "Por favor, ingresa una descripción para la tarea.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Método para agregar una tarea a Firestore
    private void addTaskToFirestore(String taskDescription) {
        // Crear un mapa con la descripción de la tarea
        Map<String, Object> tarea = new HashMap<>();
        tarea.put("descripcion", taskDescription);

        // Referencia a la colección "tareas" en Firestore
        db.collection("tareas")
                .add(tarea)
                .addOnSuccessListener(documentReference -> {
                    // Documento agregado exitosamente
                    // Puedes mostrar un mensaje al usuario o realizar otras acciones
                    Toast.makeText(AddTaskActivity.this, "Tarea agregada exitosamente.", Toast.LENGTH_SHORT).show();
                    finish(); // Cerrar la actividad actual
                })
                .addOnFailureListener(e -> {
                    // Error al agregar el documento
                    // Puedes mostrar un mensaje de error al usuario o realizar otras acciones
                    Toast.makeText(AddTaskActivity.this, "Error al agregar la tarea. Inténtalo de nuevo.", Toast.LENGTH_SHORT).show();
                });
    }
}