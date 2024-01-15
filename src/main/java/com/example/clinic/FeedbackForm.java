package com.example.clinic;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedbackForm extends AppCompatActivity {

    EditText mob,feedback,name;
    RatingBar HelpfulRating,UserFriendlyRating,designRating;
    Button submit1;
    String mob1,feedback1,name1,design,userfrend,helpful;
    FirebaseAuth mAuth;
    DatabaseReference clinicdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_form);
        mob=(EditText) findViewById(R.id.mob);
        name=(EditText) findViewById(R.id.name);
        feedback=(EditText)findViewById(R.id.feedback);
       submit1=(Button) findViewById(R.id.submit);
        HelpfulRating= (RatingBar) findViewById(R.id.HelpfulRating);
        UserFriendlyRating= (RatingBar) findViewById(R.id.UserFriendlyRating);
        designRating = (RatingBar) findViewById(R.id.designRating);
        mAuth= FirebaseAuth.getInstance();
        clinicdb = FirebaseDatabase.getInstance().getReference().child("Feedback");

        submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertFeedback();
            }
        });
    }
    private void insertFeedback() {

        mob1=mob.getText().toString();
        feedback1=feedback.getText().toString();
        name1=name.getText().toString();
        System.out.println("email="+mob1);
        System.out.println("feedback="+feedback1);
        System.out.println("name="+name1);
        helpful=""+HelpfulRating.getNumStars();
        design=""+designRating.getNumStars();
        userfrend=""+UserFriendlyRating.getNumStars();
        if (name1.length()>=2 && mob1.length()==10 && HelpfulRating.getNumStars()>0 && designRating.getNumStars()>0 && UserFriendlyRating.getNumStars()>0)
        {

            FeedbackFormStore f = new FeedbackFormStore(mob1,name1,feedback1,helpful,design,userfrend);
            clinicdb.child(mob1).setValue(f);
            Toast.makeText(FeedbackForm.this, "Feedback Send sucessfully", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(FeedbackForm.this, IndexPage.class);
            startActivity(i);
        }else {
            Toast.makeText(FeedbackForm.this, "Cant send your feedback please fill correct information" , Toast.LENGTH_SHORT).show();

        }
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