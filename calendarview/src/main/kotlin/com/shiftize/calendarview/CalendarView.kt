package com.shiftize.calendarview

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView

class CalendarView : LinearLayout {
    constructor(context: Context): super(context) {}
    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {}

    fun setUp() {
        this.orientation = VERTICAL
        this.addView(generateWeekNamesContainer())
        val calendarPanelPager = CalendarPanelPager(context)
        calendarPanelPager.setUp()
        this.addView(calendarPanelPager)
    }

    fun generateWeekNamesContainer(): LinearLayout {
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

    fun generateWeekText(name: String): LinearLayout {
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
}
