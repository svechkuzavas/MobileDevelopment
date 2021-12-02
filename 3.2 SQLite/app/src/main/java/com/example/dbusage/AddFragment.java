package com.example.dbusage;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.Calendar;

public class AddFragment extends Fragment {
    Button selectTime, selectDate, save;
    EditText edtHeader;
    FragmentManager fm;
    Context context;
    DBHelper dbHelper;

    public AddFragment(FragmentManager fm, Context cn) {
        this.fm = fm;
        this.context = cn;
    }
    public static AddFragment newInstance(FragmentManager fm, Context context) {
        AddFragment fragment = new AddFragment(fm, context);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHelper = new DBHelper(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        selectTime = view.findViewById(R.id.set_time);
        selectDate = view.findViewById(R.id.set_date);
        save = view.findViewById(R.id.save);

        edtHeader = view.findViewById(R.id.header);

        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment;
                switch (view.getId()){
                    case R.id.set_time:
                        newFragment = new TimePickerFragment(selectTime);
                        newFragment.show(fm, "timePicker");
                        break;
                    case R.id.set_date:
                        newFragment = new DatePickerFragment(selectDate);
                        newFragment.show(fm, "datePicker");
                        break;
                    case R.id.save:
                        String header = edtHeader.getText().toString();
                        String time = selectTime.getText().toString();
                        String date = selectDate.getText().toString();
                        ContentValues cv = new ContentValues();
                        cv.put("header", header);
                        cv.put("data", date);
                        cv.put("time", time);
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        db.insert("TODOS", null, cv);
                        break;
                }
            }
        };

        save.setOnClickListener(click);
        selectTime.setOnClickListener(click);
        selectDate.setOnClickListener(click);

        super.onViewCreated(view, savedInstanceState);
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {
        Button b;

        public TimePickerFragment(Button button){
            b = button;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            b.setText(hourOfDay + ":" + minute);
        }
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        Button b;

        public DatePickerFragment(Button button){
            b = button;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            b.setText(day + "." + month + "." + year);
        }
    }
}
