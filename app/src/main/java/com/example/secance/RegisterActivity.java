package com.example.secance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    private EditText userName;
    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    private Button registerUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        TextView have_account = findViewById(R.id.alreadyHaveAccount);
        have_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        mAuth = FirebaseAuth.getInstance();
        registerUser = (Button) findViewById(R.id.btnRegister);
        registerUser.setOnClickListener(this);

        userName = (EditText) findViewById(R.id.inputUsername);
        email = (EditText) findViewById(R.id.inputEmail);
        password = (EditText) findViewById(R.id.inputPassword);
        confirmPassword = (EditText) findViewById(R.id.inputConfirmPassword);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegister:
                addUserToFirebase();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                break;
        }

    }

    private void addUserToFirebase() {
        String username = userName.getText().toString().trim();
        String useremail = email.getText().toString().trim();
        String userpassword = password.getText().toString().trim();
        String confirmpassword = confirmPassword.getText().toString().trim();

        if (username.isEmpty()) {
            userName.setError("Please Enter User Name");
            userName.requestFocus();
            return;
        }
        if (useremail.isEmpty()) {
            email.setError("Please Enter Email");
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(useremail).matches()) {
            email.setError("Enter Valid Email");
            email.requestFocus();
            return;
        }

        if (userpassword.isEmpty()) {
            password.setError("Please Enter Password");
            password.requestFocus();
            return;
        }
        if (userpassword.length() < 6) {
            password.setError("Must be equal more than 6 characters");
            password.requestFocus();
            return;
        }
        if (confirmpassword.isEmpty()) {
            confirmPassword.setError("Please enter password");
            confirmPassword.requestFocus();
            return;
        }
        if (!confirmpassword.equals(userpassword)) {
            confirmPassword.setError("Please make sure password matches");
            confirmPassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(useremail, userpassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(username, useremail, userpassword);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
//                                        Toast.makeText(RegisterActivity.this, "User is registered", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}