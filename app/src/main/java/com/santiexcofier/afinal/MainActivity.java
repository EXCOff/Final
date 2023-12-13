package com.santiexcofier.afinal;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private RecyclerView tasksRecyclerView;
    private TaskAdapter taskAdapter;
    private List<Task> tasksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar Firestore
        db = FirebaseFirestore.getInstance();

        // Referenciar RecyclerView desde el diseño
        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);

        // Configurar el RecyclerView y el adaptador
        tasksList = new ArrayList<>();
        taskAdapter = new TaskAdapter(tasksList);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tasksRecyclerView.setAdapter(taskAdapter);

        // Manejar clics en elementos del RecyclerView
        taskAdapter.setOnItemClickListener(position -> {
        });
            // Aquí puedes manejar el clic en el elemento de la lista (marcar/desmarcar como completado, etc.)



        // Obtener datos de Firestore
        retrieveTasksFromFirestore();
    }

    // Método para obtener tareas desde Firestore
    private void retrieveTasksFromFirestore() {
        // Referencia a la colección "tareas" en Firestore
        CollectionReference tasksRef = db.collection("tareas");

        // Obtener datos de Firestore
        tasksRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Limpiar la lista de tareas
                tasksList.clear();

                // Iterar sobre los documentos obtenidos
                for (QueryDocumentSnapshot document : task.getResult()) {
                    // Obtener la descripción de la tarea desde el documento
                    String taskDescription = document.getString("descripcion");

                    // Crear una instancia de la clase Task y agregarla a la lista
                    Task taskObject = new Task(taskDescription);
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
}
