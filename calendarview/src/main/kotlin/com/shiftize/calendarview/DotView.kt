package com.shiftize.calendarview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import java.util.*

class DotView : View {
    var agendaList: List<Agenda> = ArrayList()

    constructor(context: Context): super(context) {}

    override fun onDraw(canvas: Canvas) {
        val count = agendaList.size
        agendaList.forEachIndexed { index, agenda ->
            val paint = Paint()
            paint.isAntiAlias = true
            paint.color = agenda.color
            canvas.drawCircle(width.toFloat() / (count + 1) * (index + 1),
                height.toFloat() / 2, (width + height).toFloat() / 32, paint)
        }
    }
}
