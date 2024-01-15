package com.example.clinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.se.omapi.Session;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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

public class ForgetPasswordForDoctor extends AppCompatActivity {
    String emailid = "jangamclinic";
    //String passwordEmail="Odonation@2023";
    String passwordEmail = "ikjsdufcrkdwcywk";
    EditText username, email;
    DatabaseReference organdb;
    Button send;
    String u, e;
    private String mailhost = "smtp.gmail.com";
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password_for_doctor);
        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        send = (Button) findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u = username.getText().toString();
                e = email.getText().toString();
                System.out.println("email=" + e);
                String stringHost = "smtp.gmail.com";

                Properties properties = System.getProperties();

                properties.put("mail.smtp.host", stringHost);
                properties.put("mail.smtp.port", "465");
                properties.put("mail.smtp.ssl.enable", "true");
                properties.put("mail.smtp.auth", "true");
                //   organdb = FirebaseDatabase.getInstance().getReference().child("DoctorSignup");
              //  javax.mail.Session session = javax.mail.Session.getInstance(properties, new Authenticator() {
                    javax.mail.Session session = javax.mail.Session.getInstance(properties, new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(emailid, passwordEmail);
                        }
                    });


                MimeMessage mimeMessage = new MimeMessage(session);
                try {
                    if (u.equals("JangamSatara")) {
                        String pass = "Jangam@123";
                        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(e));
                        mimeMessage.setSubject("Subject: Your password");
                        mimeMessage.setText("Your password is=" + pass);
                        Intent intent4 = new Intent(getApplicationContext(), DoctorLogin.class);
                        startActivity(intent4);
                    }
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


        });
    }

}