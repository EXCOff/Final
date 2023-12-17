package com.santiexcofier.afinal;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        // Inicialización de FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // Obtener referencias de vistas
        emailEditText = findViewById(R.id.emailEditText);
        Button resetPasswordButton = findViewById(R.id.resetPasswordButton);

        // Configuración de eventos de clic
        resetPasswordButton.setOnClickListener(view -> {
            String email = emailEditText.getText().toString();

            if (!TextUtils.isEmpty(email)) {
                resetPassword(email);
            } else {
                showToast("Por favor, ingresa tu correo electrónico para restablecer la contraseña.");
            }
        });
    }

    // Método para mostrar mensajes Toast
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // Método para restablecer la contraseña
    private void resetPassword(String email) {
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Correo de restablecimiento enviado con éxito
                        showToast("Correo de restablecimiento enviado. Verifica tu correo electrónico.");
                        finish(); // Cierra la actividad después de enviar el correo
                    } else {
                        // Fallo en el envío del correo de restablecimiento
                        showToast("Error al enviar el correo de restablecimiento. Verifica tu correo electrónico.");
                    }
                });
    }
}