package com.example.clinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CheckAppointmentScheduleByDoctor extends AppCompatActivity {
    private ListView listView;
    FirebaseDatabase database;
    // creating a new array list.
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    StorePatientAppointmenrRequestClass feed;
    // creating a variable for database reference.
    DatabaseReference ref;
    DatePicker simpleDatePicker;
    Button submit1;
   String date;
    // creating a variable for database reference.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_appointment_schedule_by_doctor);
        simpleDatePicker=(DatePicker) findViewById(R.id.simpleDatePicker);
        submit1=(Button)findViewById(R.id.request);
        feed=new StorePatientAppointmenrRequestClass();
        listView = (ListView) findViewById(R.id.listView);
        database=FirebaseDatabase.getInstance();
        list=new ArrayList<>();

        ref = FirebaseDatabase.getInstance().getReference().child("FixpatientAppointmentInfo");
        adapter=new ArrayAdapter<String>(this,R.layout.checkappointmentstatusclass,R.id.Appointment_info,list);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int i = 1;
                date=(simpleDatePicker.getDayOfMonth()+"/"+ (simpleDatePicker.getMonth() + 1)+"/"+simpleDatePicker.getYear());

                for (DataSnapshot ds : snapshot.getChildren()) {
                    feed = ds.getValue(StorePatientAppointmenrRequestClass.class);
                    //  System.out.println("name"+feed.getPname());
                    if(date.equals(feed.getDate())) {
                        System.out.println("timing2" + feed.getT2());
                        System.out.println("timing1" + feed.getT1());
                        list.add("\n" + i + "\nDate= " + feed.getDate().toString() + "\nTiming between= " + feed.getT1().toString() + "\nTo= " + feed.getT2().toString() + "\n");
                        System.out.println("timing1" + feed.getT1());
                    }
                    i++;
                }
                listView.setAdapter(adapter);


                //   }
                   /* else if(feed.getPname()==null )
                    {
                        Toast.makeText(getApplicationContext(),"Data not available",Toast.LENGTH_LONG).show();

                    }*/

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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