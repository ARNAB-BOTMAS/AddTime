package com.example.addtime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

public class MonthActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private TextView dateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_acticity);

        calendarView = findViewById(R.id.calendarView);
        dateTextView = findViewById(R.id.dateTextView);

        // Set up a listener for the CalendarView
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // Month is zero-based, so we add 1 to get the correct month
                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                dateTextView.setText("Selected Date: " + selectedDate);

                // You can perform actions with the selected date here
                // For example, show events for the selected date, etc.
                Toast.makeText(MonthActivity.this, "Selected Date: " + selectedDate, Toast.LENGTH_SHORT).show();
            }
        });
    }
}