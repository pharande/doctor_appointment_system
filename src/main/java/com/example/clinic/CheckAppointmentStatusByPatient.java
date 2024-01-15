package com.example.clinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CheckAppointmentStatusByPatient extends AppCompatActivity {
    private ListView listView;
    FirebaseDatabase database;
    // creating a new array list.
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    StorePatientAppointmenrRequestClass feed;
    // creating a variable for database reference.
    DatabaseReference ref;
    // creating a variable for database reference.
    String mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_appointment_status_by_patient);
        feed=new StorePatientAppointmenrRequestClass();
        listView = (ListView) findViewById(R.id.listView1);
        database=FirebaseDatabase.getInstance();
        list=new ArrayList<>();
        ref = FirebaseDatabase.getInstance().getReference().child("FixpatientAppointmentInfo");
        adapter=new ArrayAdapter<String>(this,R.layout.checkappointmentstatusclass,R.id.Appointment_info,list);
        Intent i=getIntent();
        mobile=i.getStringExtra("mobileno");
      ref.addValueEventListener(new ValueEventListener() {
                                      @Override
                                      public void onDataChange(@NonNull DataSnapshot snapshot) {
                                          int i = 1;
                                          for (DataSnapshot ds : snapshot.getChildren()) {
                                              feed = ds.getValue(StorePatientAppointmenrRequestClass.class);
                                            //  System.out.println("name"+feed.getPname());
                                              if(mobile.equals(feed.getMob())) {
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