package com.shiftize.calendarview

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.Log
import java.util.*

class CalendarPanelPager : ViewPager {
    var agendaList: List<Agenda> = ArrayList()
        set(value) {
            (this.adapter as CalendarPanelAdapter).agendaList = value
            val calendarPanel = findViewWithTag(currentItem) as CalendarPanel?
            calendarPanel?.update(value)
        }

    var onCalendarClickedListener: CalendarView.OnCalendarClickedListener? = null
        set(value) {
            (adapter as CalendarPanelAdapter?)?.onCalendarClickedListener = value
        }

    constructor(context: Context): super(context) {}

    fun setUp() {
        val calendar = Calendar.getInstance()
        setUp(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1)
    }

    fun setUp(year: Int, month: Int) {
        this.adapter = CalendarPanelAdapter(context, year, month)
        this.currentItem = getCenterPosition()
    }

    /**
     * calculate center position
     * @return calculated center position
     */
    fun getCenterPosition(): Int {
        return this.adapter.count / 2
    }
}
