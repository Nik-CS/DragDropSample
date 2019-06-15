package se.wintren.quickdrop.view

import android.content.Context
import android.graphics.*
import android.view.View
import se.wintren.quickdrop.R
import se.wintren.quickdrop.toCircle


class CircleDragShadowBuilder(context: Context, private val bitmap: Bitmap) : View.DragShadowBuilder() {

    private val size: Int
    private val borderPadding: Int

    init {
        context.resources.run {
            size = getDimensionPixelSize(R.dimen.circleShadowSize)
            borderPadding = getDimensionPixelSize(R.dimen.circleShadowPadding)
        }
    }

    override fun onProvideShadowMetrics(outShadowSize: Point, outShadowTouchPoint: Point) {
        outShadowSize.set(size, size)
        val offset = size - size / 4
        outShadowTouchPoint.set(offset, offset)
    }

    override fun onDrawShadow(canvas: Canvas) {
        val canvasRect = canvas.clipBounds
        val bitmapRect = Rect(canvasRect).apply {
            left = canvasRect.left + borderPadding
            right = canvasRect.right - borderPadding
            top = canvasRect.top + borderPadding
            bottom = canvasRect.bottom - borderPadding
        }

        canvas.drawCircle(
            (canvasRect.width() / 2).toFloat(),
            (canvasRect.height() / 2).toFloat(),
            (canvasRect.height() / 2).toFloat(),
            Paint().apply { color = Color.WHITE })

        canvas.drawBitmap(bitmap.toCircle(), null, bitmapRect, Paint(Paint.ANTI_ALIAS_FLAG))
    }

}
