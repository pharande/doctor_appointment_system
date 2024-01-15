package com.example.clinic;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class checkPatientInformation extends AppCompatActivity {
    private ListView listView;
    FirebaseDatabase database;
    RadioButton male, female, other;
    // creating a new array list.
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    EditText registrationno;
    FirebaseAuth mAuth;
    DatabaseReference clinicdb;
    String gender="",regno1,pname1,age1,height1,weight1,address1,blood1,disease1,pastingsugar1,ppsugar1,HBA1c1,otherreport1,allergy1,medicine1,precautions1;

    Button Editbtn;
    Button Check;
    String regno2;
    FillPatientInformationByDoctorStoreClass f;
    EditText regno, pname, age, height, weight, address, blood, disease, pastingsugar, ppsugar, HBA1c, otherreport, allergy, medicine, precautions;
    FillPatientInformationByDoctorStoreClass feed;
    // creating a variable for database reference.
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_patient_information);
        feed = new FillPatientInformationByDoctorStoreClass();
        //listView = (ListView) findViewById(R.id.listView);
        database = FirebaseDatabase.getInstance();
        registrationno = (EditText) findViewById(R.id.registrationno);
        list = new ArrayList<>();
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        other = (RadioButton) findViewById(R.id.other);
        Editbtn=(Button)findViewById(R.id.Editbtn);
        f = new FillPatientInformationByDoctorStoreClass();
        ref = FirebaseDatabase.getInstance().getReference().child("Feedback");
        adapter = new ArrayAdapter<String>(this, R.layout.feedbackinfo, R.id.Feedback_info, list);
        Check = (Button) findViewById(R.id.Check);
        regno = (EditText) findViewById(R.id.regno);
        pname = (EditText) findViewById(R.id.pname);
        age = (EditText) findViewById(R.id.age);
        height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);
        address = (EditText) findViewById(R.id.address);
        blood = (EditText) findViewById(R.id.blood);
        disease = (EditText) findViewById(R.id.disease);
        pastingsugar = (EditText) findViewById(R.id.pastingsugar);
        ppsugar = (EditText) findViewById(R.id.ppsugar);
        HBA1c = (EditText) findViewById(R.id.HBA1c);
        otherreport = (EditText) findViewById(R.id.otherreport);
        allergy = (EditText) findViewById(R.id.allergy);
        medicine = (EditText) findViewById(R.id.medicine);
        precautions = (EditText) findViewById(R.id.precautions);
        mAuth= FirebaseAuth.getInstance();
        clinicdb = FirebaseDatabase.getInstance().getReference().child("PatientInfoByDoctor");

        Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regno2 = registrationno.getText().toString();
                System.out.println("user given reg no"+regno2);
                ref = FirebaseDatabase.getInstance().getReference().child("PatientInfoByDoctor");
                ref.orderByChild("regno1").equalTo(regno2)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    System.out.println("Data present");
                                    int i = 1;
                                    System.out.println("Hi2");
                                    for (DataSnapshot ds : snapshot.getChildren()) {
                                        System.out.println("Hi3");
                                        f = ds.getValue(FillPatientInformationByDoctorStoreClass.class);
                                        pname.setText(f.getPname1());
                                        regno.setText(f.getRegno1());
                                        age.setText(f.getAge1());
                                        address.setText(f.getAddress1());
                                        height.setText(f.getHeight1());
                                        weight.setText(f.getWeight1());
                                        ppsugar.setText(f.getPpsugar1());
                                        pastingsugar.setText(f.getPastingsugar1());
                                        HBA1c.setText(f.getHBA1c1());
                                        disease.setText(f.getDisease1());
                                        precautions.setText(f.getPrecautions1());
                                        allergy.setText(f.getAllergy1());
                                        medicine.setText(f.getMedicine1());
                                        blood.setText(f.getBlood1());
                                        otherreport.setText(f.getOtherreport1());
                                        String g = f.getGender();
                                        System.out.println("Gender="+g);
                                        if (g.equals( "male")) {
                                            male.setChecked(true);
                                        } else if (g.equals("female")) {
                                            female.setChecked(true);
                                        } else if (g.equals( "other")) {
                                            other.setChecked(true);
                                        }
                                    }

                                    i++;
                                }


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }


                        });
            }
        });
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
                FillPatientInformationByDoctorStoreClass f=new FillPatientInformationByDoctorStoreClass(regno1,pname1,age1,height1,weight1,gender,address1,blood1,disease1,pastingsugar1,ppsugar1,HBA1c1,otherreport1,allergy1,medicine1,precautions1);
                clinicdb.child(regno1).setValue(f);
                Toast.makeText(checkPatientInformation.this,"store information suuessfully",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(checkPatientInformation.this, DoctorIndexPage.class);
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
