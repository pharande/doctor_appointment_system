package com.example.clinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class IndexPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_page);
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