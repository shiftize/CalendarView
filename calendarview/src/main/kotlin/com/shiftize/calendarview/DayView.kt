package com.shiftize.calendarview

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import java.util.*

class DayView : RelativeLayout {
    var dayText: TextView? = null
    var day: Int? = 1
        set(value) {
            dayText?.text = value.toString()
        }
    var textColor: Int = Color.rgb(0, 0, 0)
        set(value) {
            dayText?.setTextColor(value)
        }

    var dotContainer: LinearLayout? = null
    var agendaList: List<Agenda> = ArrayList()
        set(value) {
            setDotViews(value)
        }

    constructor(context: Context): super(context) {
        setUp()
    }

    fun setUp() {
        removeAllViews()

        dayText = TextView(context)
        dayText?.text = day.toString()
        dayText?.textSize = 20.0f
        dayText?.setTextColor(textColor)
        val textLayoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        textLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_START)
        this.addView(dayText, textLayoutParams)

        dotContainer = LinearLayout(context)
        dotContainer?.orientation = LinearLayout.HORIZONTAL
        dotContainer?.setGravity(Gravity.CENTER)
        val dotContainerLayoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        dotContainerLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT)
        setDotViews(agendaList)
        this.addView(dotContainer, dotContainerLayoutParams)
    }

    private fun setDotViews(agendaList: List<Agenda>) {
        dotContainer?.removeAllViews()
        val dotView = DotView(context)
        dotView.agendaList = agendaList
        dotContainer?.addView(dotView)
    }
}
