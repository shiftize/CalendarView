package com.shiftize.calendarview

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView

class DayView(context: Context) : LinearLayout(context) {
    var day: Int = 1
    var color: Int = Color.rgb(0, 0, 0)

    fun setUp(): DayView {
        val dayText: TextView = TextView(context)
        dayText.text = day.toString()
        dayText.setTextColor(color)

        val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        params.weight = 1.0f
        params.gravity = Gravity.LEFT

        this.setGravity(Gravity.CENTER)
        this.layoutParams = params
        this.addView(dayText)
        return this
    }
}
