package com.shiftize.calendarview.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.shiftize.calendarview.Agenda;
import com.shiftize.calendarview.CalendarPanel;
import com.shiftize.calendarview.CalendarView;

import java.util.ArrayList;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView textView = (TextView) findViewById(R.id.textView);

        CalendarView calendarView = (CalendarView) findViewById(R.id.calendar_view);
        calendarView.setOnSwipedListener(new Function2<Integer, Integer, Unit>() {
            @Override
            public Unit invoke(Integer year, Integer month) {
                textView.setText(year + " / " + month);
                return null;
            }
        });
        List<Agenda> agendaList = new ArrayList<>();
        Agenda agenda1 = new Agenda(2016, 2, 3, Color.rgb(255, 0, 0));
        Agenda agenda2 = new Agenda(2016, 2, 3, Color.rgb(0, 255, 0));
        Agenda agenda3 = new Agenda(2016, 2, 5, Color.rgb(0, 0, 255));
        agendaList.add(agenda1);
        agendaList.add(agenda2);
        agendaList.add(agenda3);
        calendarView.setAgendaList(agendaList);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
