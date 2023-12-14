package com.santiexcofier.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        // Inicialización de FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // Obtener referencias de vistas
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);

        Button signupButton = findViewById(R.id.signupButton);
        TextView loginTextView = findViewById(R.id.loginTextView);

        // Configuración de eventos de clic
        signupButton.setOnClickListener(view -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String confirmPassword = confirmPasswordEditText.getText().toString();

            // Verifica que se cumplan los requisitos mínimos para el correo y la contraseña
            if (isValidEmail(email) && isValidPassword(password, confirmPassword)) {
                signUp(email, password);
            }
        });

        loginTextView.setOnClickListener(view -> {
            // Redirige al usuario a la pantalla de inicio de sesión
            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            finish();
        });
    }

    // Método para mostrar mensajes Toast
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // Método para realizar el registro de usuario
    private void signUp(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Registro exitoso
                        showToast("Registro exitoso. Ahora puedes iniciar sesión.");
                        // Puedes redirigir al usuario a la pantalla de inicio de sesión
                        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                        finish();
                    } else {
                        // Fallo en el registro
                        showToast("Registro fallido. Intenta nuevamente. Error: " + task.getException());
                    }
                });
    }

    // Método para validar el formato del correo electrónico
    private boolean isValidEmail(String email) {
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true;
        } else {
            showToast("Por favor, ingresa un correo electrónico válido.");
            return false;
        }
    }

    // Método para validar los requisitos mínimos de la contraseña
    private boolean isValidPassword(String password, String confirmPassword) {
        if (password.length() >= 6) {
            if (password.equals(confirmPassword)) {
                return true;
            } else {
                showToast("Las contraseñas no coinciden.");
                return false;
            }
        } else {
            showToast("La contraseña debe tener al menos 6 caracteres.");
            return false;
        }
    }
}