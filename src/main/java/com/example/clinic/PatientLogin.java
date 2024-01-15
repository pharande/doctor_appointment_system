package com.example.clinic;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PatientLogin extends AppCompatActivity {
Button login,signup;
    TextView linkTextView;
EditText username,pass;
    DatabaseReference clinicdb;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_login);
        username=(EditText)findViewById(R.id.username1);
        pass=(EditText)findViewById(R.id.password1);
        login=(Button) findViewById(R.id.loginbtn1);
        signup=(Button) findViewById(R.id.signupbtn);
      //  linkTextView = findViewById(R.id.activity_main_link);
      /*  linkTextView.setMovementMethod(LinkMovementMethod.getInstance());
        linkTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ForgetPasswordForPatient.class);
                startActivity(intent);
            }
        });*/
        mAuth=FirebaseAuth.getInstance();
        clinicdb = FirebaseDatabase.getInstance().getReference().child("patientSignupInfo");
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PatientSignup.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Hello");
                getdata();
            }
        });
    }
    public void getdata()
    {
        String mobile = username.getText().toString();
        String pass1 = pass.getText().toString();
        System.out.println("mobile no="+mobile);
        System.out.println("pass="+pass1);
        clinicdb.child(mobile).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    String authpass=task.getResult().child("pass").getValue().toString();

                    if(pass1.equals(authpass))
                    {
                        System.out.println("mobile no="+mobile);
                        System.out.println("auth pass="+authpass);
                        System.out.println("pass="+pass1);
                        Toast.makeText(PatientLogin.this,"Login successfully",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), PatientIndexPage.class);
                        intent.putExtra("mobileno",mobile);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(PatientLogin.this, "Username or password does not match", Toast.LENGTH_SHORT).show();

                    }
                    // Log.d("firebase", "data is"+String.valueOf(task.getResult().getValue()));
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

                Intent intent4 = new Intent(getApplicationContext(),Reception_Login.class);
                startActivity(intent4);
                break;
            case R.id.Patient:

                Intent intent3 = new Intent(getApplicationContext(),PatientLogin.class);
                startActivity(intent3);
                break;
            case R.id.Feedback:
                Intent intent6 = new Intent(getApplicationContext(), FeedbackForm.class);
                startActivity(intent6);
            case R.id.ContactUs:
            {
                Intent intent5 = new Intent(getApplicationContext(), ContactUs.class);
                startActivity(intent5);
            }

        }
        return super.onOptionsItemSelected(item);
    }

}