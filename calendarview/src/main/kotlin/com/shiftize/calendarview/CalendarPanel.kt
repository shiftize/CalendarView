package com.shiftize.calendarview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout
import java.util.*

class CalendarPanel : LinearLayout {
    val DAYS_IN_A_WEEK = 7
    val WEEKS_IN_A_MONTH = 5

    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int): super(context, attrs, defStyle)

    fun setUp(year: Int, month: Int, agendaList: List<Agenda>) {
        this.orientation = VERTICAL
        removeAllViews()
        val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        layoutParams.weight = 1.0f
        val calendar: Calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month - 1)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        calendar.add(Calendar.DAY_OF_MONTH, 1 - calendar.get(Calendar.DAY_OF_WEEK))
        (0..WEEKS_IN_A_MONTH - 1).forEach {
            val weekContainer = generateWeekContainer()
            (0..DAYS_IN_A_WEEK - 1).forEach {
                val dayView = DayView(context)
                val currentMonth = calendar.get(Calendar.MONTH) + 1
                val day = calendar.get(Calendar.DAY_OF_MONTH)
                val filteredAgendaList = agendaList.filter { it.month == currentMonth && it.day == day }
                dayView.day = day
                dayView.agendaList = filteredAgendaList
                dayView.setBackgroundResource(R.drawable.day_background)
                dayView.setOnClickListener { Log.i("clicked", day.toString()) }
                weekContainer.addView(dayView, layoutParams)
                calendar.add(Calendar.DAY_OF_MONTH, 1)
            }
            this.addView(weekContainer)
        }
    }

    fun generateWeekContainer(): LinearLayout {
        val container = LinearLayout(context)
        container.orientation = HORIZONTAL

        val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        params.weight = 1.0f
        container.layoutParams = params
        return container
    }
}
