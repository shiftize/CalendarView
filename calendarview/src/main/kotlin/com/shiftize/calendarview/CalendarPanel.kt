package com.shiftize.calendarview

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import java.util.*

class CalendarPanel : LinearLayout {
    val DAYS_IN_A_WEEK = 7
    val WEEKS_IN_A_MONTH = 5

    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int): super(context, attrs, defStyle)

    fun setUp() {
        val calendar: Calendar = Calendar.getInstance()
        setUp(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1)
    }

    fun setUp(year: Int, month: Int) {
        this.orientation = VERTICAL
        removeAllViews()
        val calendar: Calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month - 1)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        calendar.add(Calendar.DAY_OF_MONTH, 1 - calendar.get(Calendar.DAY_OF_WEEK))
        (0..WEEKS_IN_A_MONTH - 1).forEach {
            val weekContainer = generateWeekContainer()
            (0..DAYS_IN_A_WEEK - 1).forEach {
                val dayView = DayView(context)
                dayView.day = calendar.get(Calendar.DAY_OF_MONTH)
                weekContainer.addView(dayView)
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
