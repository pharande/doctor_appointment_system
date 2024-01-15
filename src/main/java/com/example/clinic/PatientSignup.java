package com.example.clinic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.utilities.Validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatientSignup extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    String user;
    Validation validation;
    public static final String SHARED_PREFS = "shared_prefs";
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public static final String SharedUser = "usernameData";
    EditText mobile1,password,cpassword;
    Button singup;
    FirebaseAuth mAuth;
    DatabaseReference clinicdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_signup);
        mobile1=(EditText) findViewById(R.id.Mobile);
        password=(EditText) findViewById(R.id.password);
        cpassword=(EditText)findViewById(R.id.cpassword);
        singup=(Button) findViewById(R.id.signupbtn);
        mAuth=FirebaseAuth.getInstance();
        clinicdb = FirebaseDatabase.getInstance().getReference().child("patientSignupInfo");
        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertPatientInfo();

            }
        });}
            private void insertPatientInfo() {
                System.out.println("Hi4");
                String mobile = mobile1.getText().toString();
                String pass1 = password.getText().toString();
                String cpass1 = cpassword.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (pass1.length() > 8 && isValidPassword(pass1) && pass1.equals(pass1) && mobile.length()==10) {
                    if (pass1.equals(cpass1)) {
                        NewPatient f = new NewPatient(mobile, pass1);
                        // societydb.push().setValue(f);
                        clinicdb.child(mobile).setValue(f);
                        Toast.makeText(PatientSignup.this, "Sign up sucessfully", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(PatientSignup.this, PatientLogin.class);
                        startActivity(i);
                    }
                }
                else {
                    Toast.makeText(PatientSignup.this, "You are not Registered! Try again or incorrect mobile no or password" , Toast.LENGTH_SHORT).show();

                }
            }
            public boolean onCreateOptionsMenu(Menu menu) {
                MenuInflater m = getMenuInflater();
                m.inflate(R.menu.receptionsidemenu, menu);
                return true;
            }

            @Override
            public boolean onOptionsItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Home_page:
                        finish();
                   //     Intent intent = new Intent(getApplicationContext(), IndexPage.class);
                     //   startActivity(intent);
                        break;
                

                }
                return super.onOptionsItemSelected(item);
            }

                public static boolean isValidPassword(final String password) {

                    Pattern pattern;
                    Matcher matcher;
                    final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
                    pattern = Pattern.compile(PASSWORD_PATTERN);
                    matcher = pattern.matcher(password);

                    return matcher.matches();

                }
        }