package com.shiftize.calendarview

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import java.util.*

class CalendarPanelAdapter(val context: Context, val initYear: Int, val initMonth: Int) : PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, initYear)
        calendar.set(Calendar.MONTH, initMonth - 1)
        calendar.add(Calendar.MONTH, position - (count / 2))
        val calendarPanel = CalendarPanel(context)
        calendarPanel.setUp(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1)
        container.addView(calendarPanel)
        return calendarPanel
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

    override fun getCount(): Int {
        return 100
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view.equals(obj)
    }

}
