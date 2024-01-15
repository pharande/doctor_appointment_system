package com.example.clinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Reception_Login extends AppCompatActivity {
    Button signup,login;
    EditText user1,pass;
    FirebaseAuth mAuth;
    TextView linkTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception_login);
        login = (Button) findViewById(R.id.loginbtn1);
    //    signup = (Button) findViewById(R.id.signupbtn);
        user1 = (EditText) findViewById(R.id.username1);
        pass = (EditText) findViewById(R.id.password1);
        mAuth = FirebaseAuth.getInstance();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Hi1");
                String user = user1.getText().toString();
                String pass1 = pass.getText().toString();

                if (user.isEmpty()) {
                    user1.setError("Email ID can not be empty");
                } else if (pass1.isEmpty()) {
                    pass.setError("password can not be empty");
                } else {

                    mAuth.signInWithEmailAndPassword(user, pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Reception_Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Reception_Login.this, ReceptionIndexPage.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(Reception_Login.this, "Login Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            }

        });

    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater m=getMenuInflater();
        m.inflate(R.menu.commonsidemenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.Home_page:

                Intent intent = new Intent(getApplicationContext(), IndexPage.class);
                startActivity(intent);
                break;
            case R.id.Doctor_Login:

                Intent intent2 = new Intent(getApplicationContext(), DoctorLogin.class);
                startActivity(intent2);
                break;
            case R.id.Reception_Login:

                Intent intent20 = new Intent(getApplicationContext(), Reception_Login.class);
                startActivity(intent20);
                break;
            case R.id.Patient:

                Intent intent3 = new Intent(getApplicationContext(),PatientLogin.class);
                startActivity(intent3);
                break;
            case R.id.Feedback:
                Intent intent6 = new Intent(getApplicationContext(), FeedbackForm.class);
                startActivity(intent6);
                break;
            case R.id.ContactUs:
            {
                Intent intent5 = new Intent(getApplicationContext(), ContactUs.class);
                startActivity(intent5);
            }

        }
        return super.onOptionsItemSelected(item);
    }
    }