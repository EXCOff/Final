package com.santiexcofier.afinal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {

    private EditText nuevoNombreEditText;
    private EditText nuevoCorreoEditText;
    private Button guardarCambiosButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // Referencias a las vistas
        nuevoNombreEditText = findViewById(R.id.nuevoNombreEditText);
        nuevoCorreoEditText = findViewById(R.id.nuevoCorreoEditText);
        guardarCambiosButton = findViewById(R.id.guardarCambiosButton);

        // Configurar eventos de clic
        guardarCambiosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Aquí puedes agregar la lógica para guardar los cambios en el perfil
                guardarCambios();
            }
        });
    }

    private void guardarCambios() {
        // Lógica para guardar los cambios en la base de datos

        finish();
    }
}