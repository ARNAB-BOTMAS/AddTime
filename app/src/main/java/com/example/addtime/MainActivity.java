package com.example.addtime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private TextView entry, exit;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        entry = findViewById(R.id.entry_time);
        exit = findViewById(R.id.exit_time);

        mAuth = FirebaseAuth.getInstance();


        Calendar calendar = Calendar.getInstance();
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Kolkata");
        calendar.setTimeZone(timeZone);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

        dateFormat.setTimeZone(timeZone);
        timeFormat.setTimeZone(timeZone);

        String currentTime = timeFormat.format(calendar.getTime());
        String currentDate = dateFormat.format(calendar.getTime());
        user = mAuth.getCurrentUser();
        if (user != null){
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Ashok Mondal");

            entry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    reference.child(user.getUid()).child(currentDate).child("Entry Time").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                // Entry for this date already exists, show a message
                                Toast.makeText(MainActivity.this, "Entry already made for today.", Toast.LENGTH_SHORT).show();
                            } else {
                                // Entry for this date doesn't exist, save the entry time
                                DatabaseReference entryTimeRef = reference.child(user.getUid()).child(currentDate).child("Entry Time");
                                entryTimeRef.setValue(currentTime);
                                Toast.makeText(MainActivity.this, "Entry Date " + currentDate + ", Entry Time " + currentTime, Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(MainActivity.this, "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

            exit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    reference.child(user.getUid()).child(currentDate).child("Exit Time").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                // Entry for this date already exists, show a message
                                Toast.makeText(MainActivity.this, "Entry already made for today.", Toast.LENGTH_SHORT).show();
                            } else {
                                // Entry for this date doesn't exist, save the entry time
                                reference.child(user.getUid()).child(currentDate).child("Exit Time").setValue(currentTime);
                                Toast.makeText(MainActivity.this, "Exit Date " + currentDate + ", Exit Time " + currentTime, Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(MainActivity.this, "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }else {
            exit.setText(user.toString());
        }
    }
}