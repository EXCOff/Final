package com.santiexcofier.afinal;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> tasksList;
    private OnItemClickListener onItemClickListener;

    // Interfaz para manejar clics en elementos individuales
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    // Método para establecer el listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    // Constructor
    public TaskAdapter(List<Task> tasksList) {
        this.tasksList = tasksList;
    }

    // Método para crear nuevas vistas (invocado por el layout manager)
    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    // Método para reemplazar el contenido de una vista (invocado por el layout manager)
    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = tasksList.get(position);
        holder.bind(task);

        // Configurar el clic en el elemento
        holder.itemView.setOnClickListener(view -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    // Método para devolver la cantidad de elementos en la lista
    @Override
    public int getItemCount() {
        return tasksList.size();
    }

    // Clase ViewHolder que representa cada elemento en la lista
    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        private TextView taskDescriptionTextView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            taskDescriptionTextView = itemView.findViewById(R.id.taskDescriptionTextView);
        }

        // Método para asignar la descripción de la tarea a la vista
        public void bind(Task task) {
            taskDescriptionTextView.setText(task.getDescription());
        }
    }
}