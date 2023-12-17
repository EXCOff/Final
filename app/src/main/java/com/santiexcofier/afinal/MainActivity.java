package com.santiexcofier.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView tasksRecyclerView;
    private TaskAdapter taskAdapter;
    private List<Task> tasksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configurar el Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configurar el RecyclerView y el adaptador
        tasksList = new ArrayList<>();
        taskAdapter = new TaskAdapter(tasksList);
        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tasksRecyclerView.setAdapter(taskAdapter);

        // Obtener datos de Firestore
        retrieveTasksFromFirestore();
    }

    // Método para mostrar mensajes Toast
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // Método para obtener tareas desde Firestore
    private void retrieveTasksFromFirestore() {
        // Referencia a la colección "tareas" en Firestore
        CollectionReference tasksRef = FirebaseFirestore.getInstance().collection("tareas");

        // Obtener datos de Firestore
        tasksRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Limpiar la lista de tareas
                tasksList.clear();

                // Iterar sobre los documentos obtenidos
                for (QueryDocumentSnapshot document : task.getResult()) {
                    // Obtener la descripción y fecha de la tarea desde el documento
                    String taskDescription = document.getString("descripcion");
                    String taskDate = document.getString("date");

                    // Crear una instancia de la clase Task y agregarla a la lista
                    Task taskObject = new Task(taskDescription, taskDate);
                    tasksList.add(taskObject);
                }

                // Notificar al adaptador que los datos han cambiado
                taskAdapter.notifyDataSetChanged();
            } else {
                // Manejar el error al obtener documentos
                Exception exception = task.getException();
                if (exception != null) {
                    exception.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflar el menú; esto agrega elementos a la barra de acción si está presente.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Manejar los clics en los elementos del menú.
        int itemId = item.getItemId();
        if (itemId == R.id.action_add_task) {
            // Agregar tarea
            startActivity(new Intent(MainActivity.this, AddTaskActivity.class));
            return true;
        } else if (itemId == R.id.action_profile) {
            // Acceder al perfil
            showToast("Acceder al perfil desde el menú");
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void openProfileActivity(View view) {
        // Aquí puedes agregar la lógica para abrir la actividad de perfil
        startActivity(new Intent(this, ProfileActivity.class));
    }


    public void openAddTaskActivity(View view) {
        // Aquí puedes agregar la lógica para abrir la actividad de agregar tarea
        startActivity(new Intent(this, AddTaskActivity.class));
    }
}