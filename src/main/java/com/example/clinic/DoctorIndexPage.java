package com.example.clinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class DoctorIndexPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_index_page);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.doctorsidemenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Home_page:

                Intent intent = new Intent(getApplicationContext(), DoctorIndexPage.class);
                startActivity(intent);
                break;
            case R.id.CheckAppointmentRequest:

                Intent intent2 = new Intent(getApplicationContext(), CheckAppointmentRequest.class);
                startActivity(intent2);
                break;
            case R.id.check_Feedback:

                Intent intent4 = new Intent(getApplicationContext(), CheckFeedback.class);
                startActivity(intent4);
                break;
            case R.id.Fill_patient_Info:

                Intent intent3 = new Intent(getApplicationContext(), FillPatientInfoBydoctor.class);
                startActivity(intent3);
                break;
            case R.id.Reception_Signup:

                Intent intent40 = new Intent(getApplicationContext(),ReceptionSignup.class);
                startActivity(intent40);
                break;
            case R.id.Check_patientInfo:
                Intent intent6 = new Intent(getApplicationContext(),checkPatientInformation.class);
                startActivity(intent6);
                break;
            case R.id.check_schedule:
                Intent intent60 = new Intent(getApplicationContext(),CheckAppointmentScheduleByDoctor.class);
                startActivity(intent60);
                break;
            case R.id.signout: {
                Intent intent5 = new Intent(getApplicationContext(), IndexPage.class);
                startActivity(intent5);
            }

        }
        return super.onOptionsItemSelected(item);
    }
}