package com.shiftize.calendarview

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet

class CalendarView : ViewPager {
    constructor(context: Context): super(context) {}
    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {}

    fun setUp() {
        val panelAdapter = CalendarPanelAdapter(context, 2016, 2)
        this.adapter = panelAdapter
        this.currentItem = panelAdapter.count / 2
    }
}
