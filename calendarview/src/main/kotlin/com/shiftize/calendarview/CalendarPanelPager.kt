package com.shiftize.calendarview

import android.content.Context
import android.support.v4.view.ViewPager
import java.util.*

class CalendarPanelPager : ViewPager {
    constructor(context: Context): super(context) {}

    fun setUp() {
        val calendar = Calendar.getInstance()
        setUp(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1)
    }

    fun setUp(year: Int, month: Int) {
        this.adapter = CalendarPanelAdapter(context, year, month)
        this.currentItem = getCenterPosition()
    }

    fun getCenterPosition(): Int {
        return this.adapter.count / 2
    }
}
