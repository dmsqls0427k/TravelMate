package com.example.hyun.triptap;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.DatePickerDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

public class AddTripActivity extends AppCompatActivity {

    private EditText fromText, toText;
    private Calendar fromCalendar, toCalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);

        fromText = (EditText) findViewById(R.id.edit_from);
        toText = (EditText) findViewById(R.id.edit_to);

        fromCalendar = Calendar.getInstance();
        toCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener fromDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                if (fromText.getText().toString().equals("")) {
                    fromCalendar.set(Calendar.YEAR, year);
                    fromCalendar.set(Calendar.MONTH, month);
                    fromCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateLabel(fromText);
                } else {
                    AlertDialog.Builder alert = new AlertDialog.Builder(AddTripActivity.this);
                    alert.setTitle("Invalid selection");
                    alert.setMessage("The date has to be before " + toText.getText());
                    alert.setPositiveButton("OK", null);
                    if (year <= toCalendar.get(Calendar.YEAR)) {
                        fromCalendar.set(Calendar.YEAR, year);
                        if (month > toCalendar.get(Calendar.MONTH)
                                && year == toCalendar.get(Calendar.YEAR)) {
                            alert.show();
                        } else {
                            fromCalendar.set(Calendar.MONTH, month);
                            if (month == toCalendar.get(Calendar.MONTH)
                                    && dayOfMonth > toCalendar.get(Calendar.DAY_OF_MONTH)) {
                                alert.show();
                            } else {
                                fromCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                updateLabel(fromText);
                            }
                        }
                    } else {
                        alert.show();
                    }
                }

            }
        };

        fromText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddTripActivity.this, fromDate, fromCalendar
                        .get(Calendar.YEAR), fromCalendar.get(Calendar.MONTH),
                        fromCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        final DatePickerDialog.OnDateSetListener toDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                if (toText.getText().toString().equals("")) {
                    toCalendar.set(Calendar.YEAR, year);
                    toCalendar.set(Calendar.MONTH, month);
                    toCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateLabel(toText);
                } else {
                    AlertDialog.Builder alert = new AlertDialog.Builder(AddTripActivity.this);
                    alert.setTitle("Invalid selection");
                    alert.setMessage("The date has to be after " + fromText.getText());
                    alert.setPositiveButton("OK", null);
                    if (year >= fromCalendar.get(Calendar.YEAR)) {
                        toCalendar.set(Calendar.YEAR, year);
                        if (month < fromCalendar.get(Calendar.MONTH)
                                && year == fromCalendar.get(Calendar.YEAR)) {
                            alert.show();
                        } else {
                            toCalendar.set(Calendar.MONTH, month);
                            if (month == fromCalendar.get(Calendar.MONTH)
                                    && dayOfMonth < fromCalendar.get(Calendar.DAY_OF_MONTH)) {
                                alert.show();
                            } else {
                                toCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                updateLabel(toText);
                            }
                        }
                    } else {
                        alert.show();
                    }
                }
            }
        };

        toText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                new DatePickerDialog(AddTripActivity.this, toDate, toCalendar
                        .get(Calendar.YEAR), toCalendar.get(Calendar.MONTH),
                        toCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel(EditText t) {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        if (t.equals(fromText)) {
            t.setText(sdf.format(fromCalendar.getTime()));
        } else if (t.equals(toText)) {
            t.setText(sdf.format(toCalendar.getTime()));
        }
    }
}



