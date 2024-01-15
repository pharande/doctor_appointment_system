package com.example.clinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RequestForAppointment extends AppCompatActivity {
    EditText pname,email,mobile,reason;
    DatePicker simpleDatePicker;
    FirebaseAuth mAuth;
    DatabaseReference clinicdb;
    TimePicker timePicker1,timePicker2;
    String patientname,date,t1,t2,mob,em,reason1;
    String appointmentStatus="Under Review";
    Button submit1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_for_appointment);
        pname=(EditText) findViewById(R.id.pname);
        simpleDatePicker=(DatePicker) findViewById(R.id.simpleDatePicker);
        timePicker1=(TimePicker) findViewById(R.id.timePicker1);
        timePicker2=(TimePicker) findViewById(R.id.timePicker2);
        email=(EditText)findViewById(R.id.email);
        mobile=(EditText)findViewById(R.id.mobile);
        reason=(EditText)findViewById(R.id.reason);
        submit1=(Button)findViewById(R.id.request);
        mAuth=FirebaseAuth.getInstance();
        clinicdb = FirebaseDatabase.getInstance().getReference().child("patientAppointmentInfo");

        submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                patientname=pname.getText().toString();
                mob=mobile.getText().toString();
                em=email.getText().toString();
                reason1=reason.getText().toString();
                date=(simpleDatePicker.getDayOfMonth()+"/"+ (simpleDatePicker.getMonth() + 1)+"/"+simpleDatePicker.getYear());
                int hour = timePicker1.getCurrentHour();
                int min = timePicker1.getCurrentMinute();
                String format;
                if (hour == 0) {
                    hour += 12;
                    format = "AM";
                } else if (hour == 12) {
                    format = "PM";
                } else if (hour > 12) {
                    hour -= 12;
                    format = "PM";
                } else {
                    format = "AM";
                }

                t1=hour+":"+min+" "+format;
                int hour1 = timePicker2.getCurrentHour();
                int min1 = timePicker2.getCurrentMinute();
                String format1;
                if (hour1 == 0) {
                    hour1 += 12;
                    format1 = "AM";
                } else if (hour1 == 12) {
                    format1 = "PM";
                } else if (hour1 > 12) {
                    hour1 -= 12;
                    format1 = "PM";
                } else {
                    format1 = "AM";
                }

                t2=hour1+":"+min1+" "+format1;



        StorePatientAppointmenrRequestClass f = new StorePatientAppointmenrRequestClass(patientname,mob,em,date,t1,t2,appointmentStatus);

        clinicdb.child(patientname).setValue(f);
        Toast.makeText(RequestForAppointment.this, "Request Send successfully", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(RequestForAppointment.this, PatientIndexPage.class);
        startActivity(i);
    }});}
}