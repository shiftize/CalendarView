package com.shiftize.calendarview

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.util.Log
import android.view.View
import android.view.ViewGroup
import java.util.*

class CalendarPanelAdapter(val context: Context, val initYear: Int, val initMonth: Int) : PagerAdapter() {
    var agendaList: List<Agenda> = ArrayList()

    override fun instantiateItem(container: ViewGroup, position: Int): CalendarPanel {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, initYear)
        calendar.set(Calendar.MONTH, initMonth - 1)
        calendar.add(Calendar.MONTH, position - (count / 2))
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val calendarPanel = CalendarPanel(context)
        val filteredAgendaList = agendaList.filter { it.year == year
                && month - 1 <= it.month && it.month <= month + 1}
        calendarPanel.setUp(year, month, filteredAgendaList)
        container.addView(calendarPanel)
        return calendarPanel
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

    override fun getCount(): Int {
        return 50
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view.equals(obj)
    }

}
