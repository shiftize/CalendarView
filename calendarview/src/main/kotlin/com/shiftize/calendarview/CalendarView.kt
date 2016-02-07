package com.shiftize.calendarview

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import java.util.*

class CalendarView : LinearLayout {
    var onMonthChangedlistener: (Int, Int) -> Unit = {year, month -> }
    var yearMonthText: TextView? = null

    var isDateShowed: Boolean = true

    constructor(context: Context): super(context) {}
    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {}

    fun setUp() {
        val calendar = Calendar.getInstance()
        setUp(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1)
    }

    fun setUp(year: Int, month: Int) {
        this.orientation = VERTICAL
        if (isDateShowed) {
            this.addView(initYearMonthText())
            yearMonthText?.text = "$year / $month"
        }
        this.addView(generateWeekNamesContainer())
        val calendarPanelPager = CalendarPanelPager(context)
        calendarPanelPager.onMonthChangedListener = onMonthChangedlistener
        if (isDateShowed) {
            calendarPanelPager.internalMonthChangedListener = { year, month ->
                yearMonthText?.text = "$year / $month"
            }
        }
        calendarPanelPager.setUp(year, month)
        this.addView(calendarPanelPager)
    }

    private fun generateWeekNamesContainer(): LinearLayout {
        val container = LinearLayout(context)
        val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        container.layoutParams = params
        listOf("Sun", "Mon", "Tue",
                "Wed", "Thu", "Fri", "Sat"
        ).forEach {
            container.addView(generateWeekText(it))
        }
        return container
    }

    private fun generateWeekText(name: String): LinearLayout {
        val weekText = TextView(context)
        weekText.text = name
        weekText.setTextColor(Color.rgb(0, 0, 0))

        val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        params.weight = 1.0f
        params.gravity = Gravity.LEFT

        val container: LinearLayout = LinearLayout(context)
        container.setGravity(Gravity.CENTER)
        container.layoutParams = params
        container.addView(weekText)
        return container
    }

    private fun initYearMonthText(): LinearLayout {
        yearMonthText = TextView(context)
        yearMonthText?.setTextColor(Color.rgb(0, 0, 0))

        val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        params.gravity = Gravity.CENTER

        val container: LinearLayout = LinearLayout(context)
        container.setGravity(Gravity.CENTER)
        container.layoutParams = params
        container.addView(yearMonthText)
        return container
    }
}
