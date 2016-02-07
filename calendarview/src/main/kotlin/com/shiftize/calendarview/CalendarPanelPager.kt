package com.shiftize.calendarview

import android.content.Context
import android.support.v4.view.ViewPager
import android.widget.LinearLayout

class CalendarPanelPager : ViewPager {
    constructor(context: Context): super(context) {}

    fun setUp() {
        val panelAdapter = CalendarPanelAdapter(context, 2016, 2)
        this.adapter = panelAdapter
        this.currentItem = panelAdapter.count / 2
    }
}
