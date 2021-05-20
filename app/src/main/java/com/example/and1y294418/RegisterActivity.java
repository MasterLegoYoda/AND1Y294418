package com.example.and1y294418;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private TextInputEditText userEmail, userPassword, userPAssword2;
    private ProgressBar loadingProgress;
    private Button regBtn;
    private FirebaseAuth mAuth;
    String userID;
    private TextView signInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userEmail = findViewById(R.id.regMail);
        userPassword = findViewById(R.id.regPassword);
        userPAssword2 = findViewById(R.id.regPassword2);
        loadingProgress = findViewById(R.id.regProgressBar);
        regBtn = findViewById(R.id.regBtn);

        loadingProgress.setVisibility(View.INVISIBLE);

        mAuth = FirebaseAuth.getInstance();

        signInBtn = findViewById(R.id.signIn);
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                regBtn.setVisibility(View.INVISIBLE);
                loadingProgress.setVisibility(View.VISIBLE);
                final String email = userEmail.getText().toString();
                final String password = userPassword.getText().toString();
                final String password2 = userPAssword2.getText().toString();


                if ( email.isEmpty() ||  password.isEmpty() || !password.equals(password2)) {
                   //error
                    showMessage("Please Verify all fields");
                    regBtn.setVisibility(View.VISIBLE);
                    loadingProgress.setVisibility(View.INVISIBLE);
                } else {

                    CreateUserAccount(email, password);
                }
            }
        });
    }

    private void CreateUserAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // user account created successfully
                            showMessage("Account created");
                            userID = mAuth.getCurrentUser().getUid();
                            updateUI();
                        } else {
                            // account creation failed
                            showMessage("account creation failed " + task.getException().getMessage());
                            regBtn.setVisibility(View.VISIBLE);
                            loadingProgress.setVisibility(View.INVISIBLE);
                        }
                    }
                });
    }

    private void updateUI() {
        Intent homeActivity = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(homeActivity);
        finish();
    }

    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}