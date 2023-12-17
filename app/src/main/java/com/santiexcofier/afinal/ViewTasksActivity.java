package com.santiexcofier.afinal;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewTasksActivity extends AppCompatActivity {

    private RecyclerView tasksListRecyclerView;
    private TaskAdapter taskAdapter;
    private List<Task> tasksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tasks);

        // Configurar el RecyclerView y el adaptador para la lista de tareas
        tasksList = new ArrayList<>();
        taskAdapter = new TaskAdapter(tasksList, new TaskAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {                 // Manejar clic en elemento de la lista                 Task selectedTask = tasksList.get(position);                 openTaskDetailActivity(selectedTask);             }         }
        );
        tasksListRecyclerView = findViewById(R.id.tasksListRecyclerView);
        tasksListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tasksListRecyclerView.setAdapter(taskAdapter);

        // Configurar el evento de clic para los elementos de la lista
        taskAdapter.setOnItemClickListener(position -> {
            Task clickedTask = tasksList.get(position);
            openTaskDetailActivity(clickedTask);
        });

        // Obtener datos de Firestore
        retrieveTasksFromFirestore();
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

    // Método para abrir la actividad de detalle de tarea
    private void openTaskDetailActivity(Task task) {
        Intent intent = new Intent(this, TaskDetailActivity.class);
        intent.putExtra("task", task);
        startActivity(intent);
    }
}