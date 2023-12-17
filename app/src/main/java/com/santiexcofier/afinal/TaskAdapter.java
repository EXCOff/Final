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

    // Constructor
    public TaskAdapter(List<Task> tasksList, OnItemClickListener onItemClickListener) {
        this.tasksList = tasksList;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el diseño de cada elemento de la lista
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        // Obtener la tarea en la posición actual
        Task currentTask = tasksList.get(position);

        // Configurar los elementos de la vista con los datos de la tarea
        holder.taskNameTextView.setText(currentTask.getDescription());
        holder.taskDateTextView.setText(currentTask.getDate());

        // Configurar el clic en el elemento
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                if (onItemClickListener != null && adapterPosition != RecyclerView.NO_POSITION) {
                    onItemClickListener.onItemClick(adapterPosition);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasksList.size();
    }

    // Clase interna para representar la vista de cada elemento de la lista
    static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView taskNameTextView;
        TextView taskDateTextView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            // Referenciar los elementos de la vista
            taskNameTextView = itemView.findViewById(R.id.taskDescriptionTextView);
            taskDateTextView = itemView.findViewById(R.id.taskDateTextView);
        }
    }

    // Interfaz para manejar clics en los elementos del RecyclerView
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    // Método para establecer el listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
}