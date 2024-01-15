package com.example.clinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CheckAppointmentRequest extends AppCompatActivity {
    private ListView listView;
    FirebaseDatabase database;
    // creating a new array list.
    ArrayList<String> list,listemail,listname,listmobile;
    ArrayAdapter<String> adapter;
    String appointmentStatus="Under Review",pname;
    StorePatientAppointmenrRequestClass feed;
    // creating a variable for database reference.
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_appointment_request);
        feed=new StorePatientAppointmenrRequestClass();
        listView = (ListView) findViewById(R.id.listView);
        database=FirebaseDatabase.getInstance();
        list=new ArrayList<>();
        listemail=new ArrayList<>();
        listmobile=new ArrayList<>();
        listname=new ArrayList<>();
        ref = FirebaseDatabase.getInstance().getReference().child("patientAppointmentInfo");
        adapter=new ArrayAdapter<String>(this,R.layout.appointmentinfo,R.id.Appointment_info,list);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int i=1;
                for(DataSnapshot ds:snapshot.getChildren())
                {
                    feed=ds.getValue(StorePatientAppointmenrRequestClass.class);
                    System.out.println("feed.getAppointmentStatus()="+feed.getAppointmentStatus());
                    System.out.println("appointmentStatus="+appointmentStatus);
                    pname=feed.getPname().toString();
                    if(feed.getAppointmentStatus().equals(appointmentStatus))
                    {
                    list.add("\n"+i+" Patient name="+feed.getPname().toString()+"\nEmail= "+feed.getEm().toString()+"\nMobile no= "+feed.getMob().toString()+"\nDate= "+feed.getDate().toString()+"\nTiming from= "+feed.getT1().toString()+"\nTiming to= "+feed.getT2().toString()+"\nAppointment Status="+feed.getAppointmentStatus()+"\n------------------------------");
                    listemail.add(feed.getEm());
                    listname.add(feed.getPname());
                    listmobile.add(feed.getMob());
                    i++;
                }
                }

                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               // String str="Item="+position+" "+((TextView)view).getText().toString();
               // String str="Item="+position+" "+view.toString();

              //  System.out.println("pos="+str);
                String email=listemail.get(position);
                String name=listname.get(position);
                String mobile=listmobile.get(position);
                System.out.println("email="+email);
                System.out.println("name="+name);
                System.out.println("mobile="+mobile);
                Intent i = new Intent(getApplicationContext(), ConfirmAppointment.class);
                i.putExtra("patient_email",email);
                i.putExtra("patient_name",name);
                i.putExtra("patient_mobile",mobile);
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
            case R.id.signout: {
                Intent intent5 = new Intent(getApplicationContext(), IndexPage.class);
                startActivity(intent5);
            }

        }
        return super.onOptionsItemSelected(item);
    }

}