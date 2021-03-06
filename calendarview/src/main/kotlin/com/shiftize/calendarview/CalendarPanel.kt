package com.shiftize.calendarview

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import java.util.*

class CalendarPanel : LinearLayout {
    val DAYS_IN_A_WEEK = 7
    val WEEKS_IN_A_MONTH = 6

    var year: Int = 0
    var month: Int = 9

    var onCalendarClickedListener: CalendarView.OnCalendarClickedListener? = null

    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int): super(context, attrs, defStyle)

    fun setUp(year: Int, month: Int, agendaList: List<Agenda>) {
        this.year = year
        this.month = month
        this.orientation = VERTICAL
        removeAllViews()
        val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        layoutParams.weight = 1.0f
        val calendar: Calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month - 1)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        calendar.add(Calendar.DAY_OF_MONTH, 1 - calendar.get(Calendar.DAY_OF_WEEK))
        this.addView(generateBorder())
        (0..WEEKS_IN_A_MONTH - 1).forEach {
            val weekContainer = generateWeekContainer()
            (0..DAYS_IN_A_WEEK - 1).forEach {
                val dayView = DayView(context)
                val currentYear = calendar.get(Calendar.YEAR)
                val currentMonth = calendar.get(Calendar.MONTH) + 1
                val day = calendar.get(Calendar.DAY_OF_MONTH)
                val filteredAgendaList = agendaList.filter { it.month == currentMonth && it.day == day }
                dayView.day = day
                if (currentMonth != month) {
                    dayView.textColor = Color.parseColor(context.getString(R.color.outside_day_text))
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                    dayView.textColor = Color.parseColor(context.getString(R.color.saturday_text))
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    dayView.textColor = Color.parseColor(context.getString(R.color.holiday_text))
                }
                dayView.agendaList = filteredAgendaList
                dayView.setBackgroundResource(R.drawable.day_background)
                dayView.setOnClickListener {
                    onCalendarClickedListener?.onCalendarClicked(currentYear, currentMonth, day)
                }
                dayView.tag = "$currentYear-$currentMonth-$day"
                if (currentMonth != month) {
                    dayView.tag = dayView.tag as String + "-outside"
                }
                weekContainer.addView(dayView, layoutParams)
                calendar.add(Calendar.DAY_OF_MONTH, 1)
            }
            this.addView(weekContainer)
            this.addView(generateBorder())
        }
    }

    fun update(agendaList: List<Agenda>) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month - 1)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        calendar.add(Calendar.DAY_OF_MONTH, 1 - calendar.get(Calendar.DAY_OF_WEEK))
        (0..WEEKS_IN_A_MONTH - 1).forEach {
            (0..DAYS_IN_A_WEEK - 1).forEach {
                val currentYear = calendar.get(Calendar.YEAR)
                val currentMonth = calendar.get(Calendar.MONTH) + 1
                val day = calendar.get(Calendar.DAY_OF_MONTH)
                val filteredAgendaList = agendaList.filter { it.month == currentMonth && it.day == day }
                val dayView = findViewWithTag("$currentYear-$currentMonth-$day") as DayView?
                dayView?.agendaList = filteredAgendaList
                calendar.add(Calendar.DAY_OF_MONTH, 1)
            }
        }
    }

    private fun generateWeekContainer(): LinearLayout {
        val container = LinearLayout(context)
        container.orientation = HORIZONTAL

        val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        params.weight = 1.0f
        container.layoutParams = params
        return container
    }

    private fun generateBorder(): View {
        val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1)
        val border = View(context)
        border.layoutParams = params
        border.setBackgroundColor(Color.parseColor(context.getString(R.color.border_color)))
        return border
    }
}
