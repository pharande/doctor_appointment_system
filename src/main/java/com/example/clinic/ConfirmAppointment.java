package com.example.clinic;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.se.omapi.Session;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ConfirmAppointment extends AppCompatActivity {
    TextView pname,email,mobile;
    String emailid="jangamclinic";
    //String passwordEmail="Odonation@2023";
    String passwordEmail="ikjsdufcrkdwcywk";
    DatabaseReference organdb;
    Button send,Reject;
    String u, e;
    private String mailhost = "smtp.gmail.com";
    private Session session;
    DatePicker simpleDatePicker;
    FirebaseAuth mAuth;
    DatabaseReference clinicdb,clinicdb1;
    String appointmentStatus1="Confirm";
    TimePicker timePicker1,timePicker2;
    String patientname,date,t1,t2,mob,em,reason1;
    String appointmentStatus="Under Review";
    Button submit1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_appointment);
        pname=(TextView) findViewById(R.id.pname);
        simpleDatePicker=(DatePicker) findViewById(R.id.simpleDatePicker);
        timePicker1=(TimePicker) findViewById(R.id.timePicker1);
        timePicker2=(TimePicker) findViewById(R.id.timePicker2);
        email=(TextView) findViewById(R.id.email);
        mobile=(TextView) findViewById(R.id.mobile);
        submit1=(Button)findViewById(R.id.approve);
        Reject=(Button)findViewById(R.id.Reject);

        Intent intent = getIntent();
        patientname= intent.getStringExtra("patient_name");
        mob= intent.getStringExtra("patient_mobile");
        em= intent.getStringExtra("patient_email");
        pname.setText(patientname);
        mobile.setText(mob);
        email.setText(em);
        String stringHost = "smtp.gmail.com";

        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", stringHost);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        mAuth= FirebaseAuth.getInstance();
        clinicdb = FirebaseDatabase.getInstance().getReference().child("FixpatientAppointmentInfo");
       clinicdb1 = FirebaseDatabase.getInstance().getReference().child("patientAppointmentInfo");
        Reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date="";
                t1="";
                t2="";
                appointmentStatus1="Reject";
                ConfirmAppointmentStoreClass f = new ConfirmAppointmentStoreClass(patientname,mob,em,date,t1,t2,appointmentStatus1);
                // clinicdb1.child(patientname).setValue(f1);
                clinicdb.child(patientname).setValue(f);
                // clinicdb.child("patientAppointmentInfo/patientname/appointmentStatus").setValue(appointmentStatus1);
                clinicdb1.child(patientname).child("appointmentStatus").setValue(appointmentStatus1);
                clinicdb.child(patientname).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if (!task.isSuccessful()) {
                            Log.e("firebase", "Error getting data", task.getException());
                        } else {
                            //   String authpass = task.getResult().child("hospPass1").getValue().toString();
                            javax.mail.Session session = javax.mail.Session.getInstance(properties, new Authenticator() {
                                @Override
                                protected PasswordAuthentication getPasswordAuthentication() {
                                    return new PasswordAuthentication(emailid, passwordEmail);
                                }
                            });

                            MimeMessage mimeMessage = new MimeMessage(session);
                            try {
                                mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(em));
                                mimeMessage.setSubject("Subject: Your appointment details");
                                mimeMessage.setText("\nPatient Name="+patientname+"\nYour appointment is Rejected \nThank You");
                                Intent intent4 = new Intent(getApplicationContext(), ConfirmAppointment.class);
                                startActivity(intent4);
                            } catch (MessagingException ex) {
                                throw new RuntimeException(ex);
                            }

                            Thread thread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Transport.send(mimeMessage);
                                    } catch (MessagingException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            thread.start();
                        }
                    }});

            }
        });
        submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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


                //StorePatientAppointmenrRequestClass f1=new StorePatientAppointmenrRequestClass(appointmentStatus1);
                ConfirmAppointmentStoreClass f = new ConfirmAppointmentStoreClass(patientname,mob,em,date,t1,t2,appointmentStatus1);
               // clinicdb1.child(patientname).setValue(f1);
                clinicdb.child(patientname).setValue(f);
               // clinicdb.child("patientAppointmentInfo/patientname/appointmentStatus").setValue(appointmentStatus1);
                clinicdb1.child(patientname).child("appointmentStatus").setValue(appointmentStatus1);
                clinicdb.child(patientname).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if (!task.isSuccessful()) {
                            Log.e("firebase", "Error getting data", task.getException());
                        } else {
                         //   String authpass = task.getResult().child("hospPass1").getValue().toString();
                            javax.mail.Session session = javax.mail.Session.getInstance(properties, new Authenticator() {
                                @Override
                                protected PasswordAuthentication getPasswordAuthentication() {
                                    return new PasswordAuthentication(emailid, passwordEmail);
                                }
                            });

                            MimeMessage mimeMessage = new MimeMessage(session);
                            try {
                                mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(em));
                                mimeMessage.setSubject("Subject: Your appointment details");
                                mimeMessage.setText("\nPatient Name="+patientname+"\nYour appointment date= " + date+"\nYour Appointment timing between= "+t1+"to "+t2+"\nThank You\nStay Healthy Stay Happy");
                                Intent intent4 = new Intent(getApplicationContext(), ConfirmAppointment.class);
                                startActivity(intent4);
                            } catch (MessagingException ex) {
                                throw new RuntimeException(ex);
                            }

                            Thread thread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Transport.send(mimeMessage);
                                    } catch (MessagingException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            thread.start();
                        }
                    }});


                Toast.makeText(ConfirmAppointment.this, "Appointment Send successfully", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ConfirmAppointment.this, PatientIndexPage.class);
                startActivity(i);
            }});}
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