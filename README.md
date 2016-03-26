# CalendarView
A calendar library used in Shiftize for Android Application

<p>
    <img src="arts/example.gif" alt="example" width="30%"/>
</p>

# Usage
In your Java codes.
```java
CalendarView calendarView = (CalendarView) findViewById(R.id.calendar_view);

// add dots
List<Agenda> agendaList = new ArrayList<>();
Agenda agenda = new Agenda(2016, 9, 28, Color.parseColor("#E74C3C"));
agendaList.add(agenda);
calendarView.setAgendaList(agandaList);

// hilight the specific cell
int textColor = Color.WHITE;
int circleColor = Color.parseColor("#E74C3C");
calendarView.highlight(2016, 9, 28, textColor, circleColor);

// reset the hilighted cell
calendarView.resetColor(2016, 9, 28);

// move to the specific page
calenendarView.moveTo(2016, 9);

// customize week names
List<String> weekNames = Arrays.asList({
    "S", "M", "T", "W",
    "T", "F", "S"
});
calendarView.setWeekNames(weekNames);
```

To realize the sample gif, check sample codes.
