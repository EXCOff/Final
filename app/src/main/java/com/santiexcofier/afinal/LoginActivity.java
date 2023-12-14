package com.santiexcofier.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        // Inicialización de FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // Obtener referencias de vistas
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);

        Button loginButton = findViewById(R.id.loginButton);
        Button signupButton = findViewById(R.id.signupButton);
        TextView forgotPasswordTextView = findViewById(R.id.forgotPasswordTextView);

        // Configuración de eventos de clic
        loginButton.setOnClickListener(view -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (!email.isEmpty() && !password.isEmpty()) {
                signIn(email, password);
            } else {
                showToast("Por favor, completa todos los campos.");
            }
        });

        signupButton.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, SignupActivity.class));
        });

        forgotPasswordTextView.setOnClickListener(view -> {
            String email = emailEditText.getText().toString();

            if (!email.isEmpty()) {
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

    // Método para realizar el inicio de sesión
    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Inicio de sesión exitoso
                        FirebaseUser user = mAuth.getCurrentUser();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } else {
                        // Fallo en el inicio de sesión
                        showToast("Inicio de sesión fallido. Verifica tus credenciales.");
                    }
                });
    }

    // Método para restablecer la contraseña
    private void resetPassword(String email) {
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Correo de restablecimiento enviado con éxito
                        showToast("Correo de restablecimiento enviado. Verifica tu correo electrónico.");
                    } else {
                        // Fallo en el envío del correo de restablecimiento
                        showToast("Error al enviar el correo de restablecimiento. Verifica tu correo electrónico.");
                    }
                });
    }
}