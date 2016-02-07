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
    var onSwipedListener: (Int, Int) -> Unit = {year, month -> }

    private var topTextContainer: LinearLayout? = null
    private var topText: TextView? = null
    var isTopTextShowed: Boolean = true
        set(value) {
            if (!value) {
                this.removeView(topTextContainer)
            } else {
                topTextContainer = generateTopTextContainer()
                this.addView(topTextContainer, 0)
            }
            isTopTextShowed = value
        }

    var weekNames: List<String> = listOf(
        "Sun", "Mon", "Tue", "Wed",
        "Thu", "Fri", "Sat"
    )
        set(value) {
            if (value.size == 7) {
                weekNames = value
            }
        }

    constructor(context: Context): super(context) {
        setUp()
    }
    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        setUp()
    }

    fun setUp() {
        val calendar = Calendar.getInstance()
        setUp(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1)
    }

    fun setUp(year: Int, month: Int) {
        this.orientation = VERTICAL

        topText = TextView(context)
        if (isTopTextShowed) {
            topTextContainer = generateTopTextContainer()
            this.addView(topTextContainer)
            topText?.text = "$year / $month"
        }

        this.addView(generateWeekNamesContainer())

        val calendarPanelPager = CalendarPanelPager(context)
        calendarPanelPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int,  positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                val calendar = Calendar.getInstance()
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month - 1)
                calendar.add(Calendar.MONTH, position - calendarPanelPager.getCenterPosition())
                val nextYear = calendar.get(Calendar.YEAR)
                val nextMonth = calendar.get(Calendar.MONTH) + 1
                topText?.text = "$nextYear / $nextMonth"
                onSwipedListener.invoke(nextYear, nextMonth)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
        calendarPanelPager.setUp(year, month)
        this.addView(calendarPanelPager)
    }

    private fun generateWeekNamesContainer(): LinearLayout {
        val container = LinearLayout(context)
        val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        container.layoutParams = params
        weekNames.forEach {
            container.addView(generateWeekText(it))
        }
        return container
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

    private fun generateTopTextContainer(): LinearLayout {
        topText?.textSize = 20.0f
        topText?.setTextColor(Color.rgb(0, 0, 0))

        val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        params.gravity = Gravity.CENTER

        val container: LinearLayout = LinearLayout(context)
        container.setGravity(Gravity.CENTER)
        container.layoutParams = params
        container.addView(topText)
        return container
    }
}
