package com.example.clinic;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
public class CheckFeedback extends AppCompatActivity {
    private ListView listView;
    FirebaseDatabase database;
    // creating a new array list.
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    FeedbackFormStore feed;
    // creating a variable for database reference.
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_feedback);
        feed=new FeedbackFormStore();
        listView = (ListView) findViewById(R.id.listView);
        database=FirebaseDatabase.getInstance();
        list=new ArrayList<>();
        ref = FirebaseDatabase.getInstance().getReference().child("Feedback");
        adapter=new ArrayAdapter<String>(this,R.layout.feedbackinfo,R.id.Feedback_info,list);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int i=1;
                for(DataSnapshot ds:snapshot.getChildren())
                {
                    feed=ds.getValue(FeedbackFormStore.class);
                    list.add("\n"+i+" Mobile no="+feed.getMob1().toString()+"\nName= "+feed.getName1().toString()+"\nFeedback= "+feed.getFeedback1().toString()+"\nDesign View Rating= "+feed.getDesign().toString()+"\nUser friendly Rating = "+feed.getUserfrend().toString()+"\nHelpful Rating= "+feed.getHelpful().toString());
                    i++;
                }
                listView.setAdapter(adapter);
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
            case R.id.signout: {
                Intent intent5 = new Intent(getApplicationContext(), IndexPage.class);
                startActivity(intent5);
            }

        }
        return super.onOptionsItemSelected(item);
    }

}
