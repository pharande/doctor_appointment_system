package com.example.clinic;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReceptionSignup extends AppCompatActivity {
    Button signup;
    EditText u, p1, p2;
    String user, pass, pass1;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception_signup);
        u = (EditText) findViewById(R.id.username);
        p1 = (EditText) findViewById(R.id.password);
        p2 = (EditText) findViewById(R.id.cpassword);
        signup = (Button) findViewById(R.id.signupbtn);
        mAuth = FirebaseAuth.getInstance();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = u.getText().toString();
                pass = p1.getText().toString();
                pass1 = p2.getText().toString();
                System.out.println("Username=" + user);
                System.out.println("Pass=" + pass);
                System.out.println("cPass=" + pass1);
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (user.matches(emailPattern) && user.length() > 0 && pass.length()>8 && isValidPassword(pass) && pass.equals(pass1))
                    {
                    mAuth.createUserWithEmailAndPassword(user, pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(ReceptionSignup.this, "You are successfully Registered", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(ReceptionSignup.this, Reception_Login.class);
                                startActivity(i);
                            }}});}
                            else {
                                Toast.makeText(ReceptionSignup.this, "You are not Registered! Try again or incorrect email or password" , Toast.LENGTH_SHORT).show();
                            }}});}
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
              //  Intent intent = new Intent(getApplicationContext(), IndexPage.class);
                //startActivity(intent);
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