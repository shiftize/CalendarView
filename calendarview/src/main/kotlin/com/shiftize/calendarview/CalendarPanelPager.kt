package com.shiftize.calendarview

import android.content.Context
import android.support.v4.view.ViewPager
import java.util.*

class CalendarPanelPager : ViewPager {
    var onMonthChangedListener: (Int, Int) -> Unit = {year, month -> };
    var internalMonthChangedListener: (Int, Int) -> Unit = {year, monht -> }

    constructor(context: Context): super(context) {}

    fun setUp() {
        val calendar = Calendar.getInstance()
        setUp(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1)
    }

    fun setUp(year: Int, month: Int) {
        this.adapter = CalendarPanelAdapter(context, year, month)
        val centerPosition = this.adapter.count / 2
        this.currentItem = centerPosition
        this.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int,  positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                val calendar = Calendar.getInstance()
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month - 1)
                calendar.add(Calendar.MONTH, position - centerPosition)
                val nextYear = calendar.get(Calendar.YEAR)
                val nextMonth = calendar.get(Calendar.MONTH) + 1
                onMonthChangedListener.invoke(nextYear, nextMonth)
                internalMonthChangedListener.invoke(nextYear, nextMonth)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }
}
