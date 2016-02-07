package com.shiftize.calendarview

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView

class DayView : LinearLayout {
    var dayText: TextView? = null
    var day: Int? = 1
        set(value) {
            dayText?.text = value.toString()
        }
    var color: Int = Color.rgb(0, 0, 0)
        set(value) {
            dayText?.setTextColor(value)
        }

    constructor(context: Context): super(context) {
        setUp()
    }

    fun setUp(): DayView {
        dayText = TextView(context)
        dayText?.text = day.toString()
        dayText?.setTextColor(color)

        val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        params.weight = 1.0f
        params.gravity = Gravity.LEFT

        this.setGravity(Gravity.CENTER)
        this.layoutParams = params
        this.addView(dayText)
        return this
    }
}
