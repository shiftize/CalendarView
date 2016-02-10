package com.shiftize.calendarview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class DotView : View {
    private val paint: Paint = Paint()
    var dotColor = Color.rgb(0, 0, 0)
        set(value) {
            paint.color = value
            invalidate()
        }

    constructor(context: Context): super(context) {}

    override fun onDraw(canvas: Canvas) {
        paint.isAntiAlias = true
        canvas.drawCircle(width.toFloat() / 2 ,
                height.toFloat() / 2, (width + height).toFloat() / 32, paint)
    }
}
