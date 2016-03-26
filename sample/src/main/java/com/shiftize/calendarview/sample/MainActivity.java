package com.shiftize.calendarview.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.shiftize.calendarview.Agenda;
import com.shiftize.calendarview.CalendarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    int previousYear = 0;
    int previousMonth = 0;
    int previousDay = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final CalendarView calendarView = (CalendarView) findViewById(R.id.calendar_view);
        calendarView.setOnCalendarSwipedListener(new CalendarView.OnCalendarSwipedListener() {
            @Override
            public void onCalendarSwiped(int year, int month) {
                Log.i("swiped", year + "/" + month);
            }
        });

        calendarView.setOnCalendarClickedListener(new CalendarView.OnCalendarClickedListener() {
            @Override
            public void onCalendarClicked(int year, int month, int day) {
                Log.i("clicked", year + "/" + month + "/" + day);
                calendarView.resetColor(previousYear, previousMonth, previousDay);
                calendarView.highlight(year, month, day, Color.WHITE, Color.parseColor("#3498DB"));
                previousYear = year;
                previousMonth = month;
                previousDay = day;

                if (calendarView.getCurrentYear() < year) {
                    calendarView.moveToNext();
                } else if (calendarView.getCurrentYear() > year) {
                    calendarView.moveToPrevious();
                } else if (calendarView.getCurrentMonth() < month) {
                    calendarView.moveToNext();
                } else if (calendarView.getCurrentMonth() > month) {
                    calendarView.moveToPrevious();
                }
            }
        });

        final List<Agenda> agendaList = new ArrayList<>();
        Agenda agenda1 = new Agenda(2016, 2, 3, Color.parseColor("#E74C3C"));
        Agenda agenda2 = new Agenda(2016, 2, 3, Color.parseColor("#3498DB"));
        Agenda agenda3 = new Agenda(2016, 2, 5, Color.parseColor("#1ABC9D"));
        Agenda agenda4 = new Agenda(2016, 2, 7, Color.parseColor("#E74C3C"));
        Agenda agenda5 = new Agenda(2016, 2, 7, Color.parseColor("#3498DB"));
        Agenda agenda6 = new Agenda(2016, 2, 7, Color.parseColor("#1ABC9D"));
        agendaList.add(agenda1);
        agendaList.add(agenda2);
        agendaList.add(agenda3);
        agendaList.add(agenda4);
        agendaList.add(agenda5);
        agendaList.add(agenda6);
        calendarView.setAgendaList(agendaList);

        Button moveToButton = (Button) findViewById(R.id.moveToButton);
        moveToButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarView.moveTo(2017, 3);
            }
        });

        Button addAgendaButton = (Button) findViewById(R.id.addAgendaButton);
        addAgendaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Agenda agenda = new Agenda(2016, 2, 8, Color.parseColor("#1ABC9D"));
                agendaList.add(agenda);
                calendarView.setAgendaList(agendaList);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
