package com.example.clinic;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class DoctorLogin extends AppCompatActivity {

EditText username,password;
Button loginbtn,signupbtn1;
    TextView linkTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);
        username=(EditText) findViewById(R.id.username1);
        password=(EditText) findViewById(R.id.password1);
        loginbtn=(Button) findViewById(R.id.loginbtn1);
        /* linkTextView = findViewById(R.id.activity_main_link);
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());
        linkTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ForgetPasswordForDoctor.class);
                startActivity(intent);
            }
        });*/
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user,pass;
                user=username.getText().toString();
                pass=password.getText().toString();
                if(user.equals("JangamSatara")&& pass.equals("Jangam@123"))
                {
                    Toast.makeText(DoctorLogin.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(DoctorLogin.this, DoctorIndexPage.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(DoctorLogin.this, "User name or password is incorrect", Toast.LENGTH_SHORT).show();

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
            case R.id.Reception_Signup:

                Intent intent4 = new Intent(getApplicationContext(),ReceptionSignup.class);
                startActivity(intent4);
                break;
            case R.id.Patient:

                Intent intent3 = new Intent(getApplicationContext(),PatientLogin.class);
                startActivity(intent3);
                break;
            case R.id.Feedback:
                Intent intent1 = new Intent(getApplicationContext(),FeedbackForm.class);
                startActivity(intent1);
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