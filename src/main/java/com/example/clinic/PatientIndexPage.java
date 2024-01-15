package com.example.clinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class PatientIndexPage extends AppCompatActivity {
String mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_index_page);
        Intent i=getIntent();
        mobile=i.getStringExtra("mobileno");
    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater m=getMenuInflater();
        m.inflate(R.menu.patientsidemenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.Home_page:

                Intent intent = new Intent(getApplicationContext(), PatientIndexPage.class);
                intent.putExtra("mobileno",mobile);
                startActivity(intent);

                break;
            case R.id.RequestForAppointment:

                Intent intent2 = new Intent(getApplicationContext(), RequestForAppointment.class);
                intent2.putExtra("mobileno",mobile);
                startActivity(intent2);
                break;
            case R.id.CheckAppointmentStatus:

                Intent intent4 = new Intent(getApplicationContext(),CheckAppointmentStatusByPatient.class);
                intent4.putExtra("mobileno",mobile);
                startActivity(intent4);
                break;
            case R.id.PatientDetails:
                Intent intent3 = new Intent(getApplicationContext(),checkPatientInformation.class);
                intent3.putExtra("mobileno",mobile);
                startActivity(intent3);
                break;

            case R.id.ContactUs:
            {
                Intent intent5 = new Intent(getApplicationContext(), ContactUs.class);
                startActivity(intent5);
            }
            break;
            case R.id.signout:
                Intent intent6 = new Intent(getApplicationContext(), IndexPage.class);
                startActivity(intent6);

        }
        return super.onOptionsItemSelected(item);
    }
}