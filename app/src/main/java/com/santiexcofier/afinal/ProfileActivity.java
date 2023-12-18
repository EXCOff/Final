package com.santiexcofier.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private TextView nombreUsuarioTextView;
    private TextView correoElectronicoTextView;
    private Button editarPerfilButton;
    private Button cerrarSesionButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Referencias a las vistas
        nombreUsuarioTextView = findViewById(R.id.nombreUsuarioTextView);
        correoElectronicoTextView = findViewById(R.id.correoElectronicoTextView);
        editarPerfilButton = findViewById(R.id.editarPerfilButton);
        cerrarSesionButton = findViewById(R.id.cerrarSesionButton);

        // Simulación de datos del usuario
        String nombreUsuario = "Nombre de Usuario";
        String correoElectronico = "usuario@example.com";

        // Mostrar información del usuario
        nombreUsuarioTextView.setText("Nombre: " + nombreUsuario);
        correoElectronicoTextView.setText("Correo electrónico: " + correoElectronico);

        // Configurar eventos de clic
        editarPerfilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lógica para abrir la actividad de edición de perfil
                startActivity(new Intent(ProfileActivity.this, EditProfileActivity.class));
            }
        });

        cerrarSesionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Aquí puedes agregar la lógica para cerrar sesión
                // Puedes redirigir al inicio de sesión o realizar otras acciones necesarias
            }
        });
    }
}