package com.shiftize.calendarview

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.Log
import java.util.*

class CalendarPanelPager : ViewPager {
    var onDayClickedListener: (Int, Int, Int) -> Unit = {year, month, day -> }

    var agendaList: List<Agenda> = ArrayList()
        set(value) {
            (this.adapter as CalendarPanelAdapter).agendaList = value
            this.adapter.notifyDataSetChanged()
        }

    constructor(context: Context): super(context) {}

    fun setUp() {
        val calendar = Calendar.getInstance()
        setUp(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1)
    }

    fun setUp(year: Int, month: Int) {
        this.adapter = CalendarPanelAdapter(context, year, month)
        (this.adapter as CalendarPanelAdapter).onDayClickedListener = onDayClickedListener
        this.currentItem = getCenterPosition()
    }

    fun getCenterPosition(): Int {
        return this.adapter.count / 2
    }
}
