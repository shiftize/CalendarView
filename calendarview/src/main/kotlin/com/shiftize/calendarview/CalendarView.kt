package com.shiftize.calendarview

import android.content.Context
import android.graphics.Color
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import java.util.*

class CalendarView : LinearLayout {
    private val calendarListeners = CalendarListeners

    private var calendarPanelPager: CalendarPanelPager? = null

    private var topText: TextView? = null
    var isTopTextShowed: Boolean = true
        set(value) {
            topText?.visibility = if (value) VISIBLE else INVISIBLE
        }

    private var weekNamesContainer: LinearLayout? = null
    var weekNames: List<String> = listOf(
        "Sun", "Mon", "Tue", "Wed",
        "Thu", "Fri", "Sat"
    )
        set(value) {
            if (value.size == 7) {
                weekNamesContainer?.removeAllViews()
                insertWeekNamesToContainer(value, weekNamesContainer)
            }
        }

    var agendaList: List<Agenda> = ArrayList()
        set(value) {
            calendarPanelPager?.agendaList = value
        }

    private var initYear: Int = 0
    private var initMonth: Int = 0

    constructor(context: Context): super(context) {
        setUp()
    }
    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        setUp()
    }

    private fun setUp() {
        val calendar = Calendar.getInstance()
        setUp(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1)
    }

    private fun setUp(year: Int, month: Int) {
        initYear = year
        initMonth = month

        this.orientation = VERTICAL

        topText = TextView(context)
        topText?.text = "$year / $month"
        this.addView(generateTopTextContainer(topText))

        weekNamesContainer = LinearLayout(context)
        insertWeekNamesToContainer(weekNames, weekNamesContainer)
        this.addView(weekNamesContainer)

        calendarPanelPager = CalendarPanelPager(context)
        calendarPanelPager?.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int,  positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                val calendar = Calendar.getInstance()
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month - 1)
                calendar.add(Calendar.MONTH, position - calendarPanelPager!!.getCenterPosition())
                val nextYear = calendar.get(Calendar.YEAR)
                val nextMonth = calendar.get(Calendar.MONTH) + 1
                topText?.text = "$nextYear / $nextMonth"
                calendarListeners.onCalendarSwipedListener?.onCalendarSwiped(nextYear, nextMonth)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
        calendarPanelPager?.setUp(year, month)
        this.addView(calendarPanelPager)
    }

    private fun insertWeekNamesToContainer(weekNames: List<String>, container: LinearLayout?) {
        val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        container?.layoutParams = params
        weekNames.forEach {
            container?.addView(generateWeekText(it))
        }
    }

    private fun generateWeekText(name: String): LinearLayout {
        val weekText = TextView(context)
        weekText.text = name
        weekText.setTextColor(Color.rgb(0, 0, 0))

        val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        params.weight = 1.0f
        params.gravity = Gravity.LEFT

        val container: LinearLayout = LinearLayout(context)
        container.setGravity(Gravity.CENTER)
        container.layoutParams = params
        container.addView(weekText)
        return container
    }

    private fun generateTopTextContainer(textView: TextView?): LinearLayout {
        textView?.textSize = 20.0f
        textView?.setTextColor(Color.rgb(0, 0, 0))

        val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        params.gravity = Gravity.CENTER

        val container: LinearLayout = LinearLayout(context)
        container.setGravity(Gravity.CENTER)
        container.layoutParams = params
        container.addView(textView)
        return container
    }

    fun moveTo(year: Int, month: Int) {
        val centerPos = calendarPanelPager?.getCenterPosition()
        val nextPos = centerPos?.plus((year - initYear) * 12 + month - initMonth)
        if (nextPos != null) {
            calendarPanelPager?.setCurrentItem(nextPos, true)
        }
    }

    fun setOnCalendarSwipedListener(listener: OnCalendarSwipedListener) {
        calendarListeners.onCalendarSwipedListener = listener
    }

    fun setOnCalendarClickedListener(listener: OnCalendarClickedListener) {
        calendarListeners.onCalendarClickedListener = listener
    }

    interface OnCalendarSwipedListener {
        fun onCalendarSwiped(year: Int, month: Int)
    }
    interface OnCalendarClickedListener {
        fun onCalendarClicked(year: Int, month: Int, day: Int)
    }
}
