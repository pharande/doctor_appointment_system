package com.example.clinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FillPatientInfoBydoctor extends AppCompatActivity {
    EditText regno,pname,age,height,weight,address,blood,disease,pastingsugar,ppsugar,HBA1c,otherreport,allergy,medicine,precautions;
    Button Editbtn;
    RadioButton male,female,other;
    FirebaseAuth mAuth;
    DatabaseReference clinicdb;
    String gender="",regno1,pname1,age1,height1,weight1,address1,blood1,disease1,pastingsugar1,ppsugar1,HBA1c1,otherreport1,allergy1,medicine1,precautions1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_patient_info_bydoctor);
        pname=(EditText) findViewById(R.id.pname);
        regno=(EditText)findViewById(R.id.regno);
        age=(EditText) findViewById(R.id.age);
        height=(EditText) findViewById(R.id.height);
        weight=(EditText) findViewById(R.id.weight);
        address=(EditText) findViewById(R.id.address);
        blood=(EditText) findViewById(R.id.blood);
        disease=(EditText) findViewById(R.id.disease);
        pastingsugar=(EditText) findViewById(R.id.pastingsugar);
        ppsugar=(EditText) findViewById(R.id.ppsugar);
        HBA1c=(EditText) findViewById(R.id.HBA1c);
        otherreport=(EditText) findViewById(R.id.otherreport);
        allergy=(EditText) findViewById(R.id.allergy);
        medicine=(EditText) findViewById(R.id.medicine);
        precautions=(EditText) findViewById(R.id.precautions);
        male=(RadioButton) findViewById(R.id.male);
        female=(RadioButton) findViewById(R.id.female);
        other=(RadioButton) findViewById(R.id.other);
        Editbtn=(Button) findViewById(R.id.Editbtn);
        mAuth= FirebaseAuth.getInstance();
        clinicdb = FirebaseDatabase.getInstance().getReference().child("PatientInfoByDoctor");
        Editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regno1=regno.getText().toString();
                pname1 =pname.getText().toString();
                age1=age.getText().toString();
                height1 =height.getText().toString();
                weight1=weight.getText().toString();
                address1=address.getText().toString();
                blood1=blood.getText().toString();
                disease1=disease.getText().toString();
                pastingsugar1=pastingsugar.getText().toString();
                ppsugar1=ppsugar.getText().toString();
                HBA1c1=HBA1c.getText().toString();
                otherreport1=otherreport.getText().toString();
                allergy1=allergy.getText().toString();
                medicine1=medicine.getText().toString();
                precautions1=precautions.getText().toString();
                if(male.isChecked())
                {
                    gender="male";
                } else if (female.isChecked()) {
                    gender="female";
                } else if (other.isChecked()) {
                    gender="other";
                }
                FillPatientInformationByDoctorStoreClass f = new FillPatientInformationByDoctorStoreClass(regno1,pname1,age1,height1,weight1,gender,address1,blood1,disease1,pastingsugar1,ppsugar1,HBA1c1,otherreport1,allergy1,medicine1,precautions1);
                // societydb.push().setValue(f);
                clinicdb.child(regno1).setValue(f);
                Toast.makeText(FillPatientInfoBydoctor.this, "Store successfully", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(FillPatientInfoBydoctor.this, DoctorIndexPage.class);
                startActivity(i);
            }
        });
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