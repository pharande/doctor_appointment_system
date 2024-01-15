package com.example.clinic;



import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ContactUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
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
            case R.id.Patient:

                Intent intent3 = new Intent(getApplicationContext(),PatientLogin.class);
                startActivity(intent3);
                break;
            case R.id.Feedback:
                Intent intent4 = new Intent(getApplicationContext(),FeedbackForm.class);
                startActivity(intent4);
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