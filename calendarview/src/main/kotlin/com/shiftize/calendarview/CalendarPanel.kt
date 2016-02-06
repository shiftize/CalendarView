package com.shiftize.calendarview

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import java.util.*

/**
 * Created by takuma on 2016/02/04.
 */
class CalendarPanel : LinearLayout {
    var year: Int = 2015
    var month: Int = 0
        set(value) {
            if (value > 12) {
                month = 12
            } else if (value < 1) {
                month = 1
            } else {
                month = value
            }
        }

    val DAYS_IN_A_WEEK = 7
    val WEEKS_IN_A_MONTH = 5

    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int): super(context, attrs, defStyle)

    fun update() {
        val calendar: Calendar = Calendar.getInstance()
        update(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1)
    }

    fun update(year: Int, month: Int) {
        this.orientation = VERTICAL
        removeAllViews()
        val calendar: Calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month - 1)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        calendar.add(Calendar.DAY_OF_MONTH, 1 - calendar.get(Calendar.DAY_OF_WEEK))
        this.addView(generateWeekNamesContainer())
        (0..WEEKS_IN_A_MONTH - 1).forEach {
            val weekContainer = generateWeekContainer()
            (0..DAYS_IN_A_WEEK - 1).forEach {
                weekContainer.addView(generateDayCell(calendar))
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

    fun generateDayCell(calendar: Calendar): DayView {
        val dayView = DayView(context)
        dayView.day = calendar.get(Calendar.DAY_OF_MONTH)
        dayView.color = Color.rgb(0, 0, 0)
        return dayView.setUp()
    }

    fun generateWeekNamesContainer(): LinearLayout {
        val container = LinearLayout(context)
        val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        params.weight = 1.0f
        container.layoutParams = params
        listOf("Sun", "Mon", "Tus",
                "Wed", "thu", "Fri", "Sat"
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
