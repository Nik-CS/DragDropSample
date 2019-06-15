package se.wintren.quickdrop

import android.content.Context
import android.graphics.*
import android.util.Log
import java.io.InputStream

fun Context.getBitmapFromAssets(path: String): Bitmap {
    val inputStream: InputStream = assets.open(path)
    return BitmapFactory.decodeStream(inputStream)
}

fun Bitmap.toCircle(): Bitmap {
    val length = if (width > height) height else width
    val output = Bitmap.createBitmap(length, length, Bitmap.Config.ARGB_8888)

    val radius: Float = (length / 2).toFloat()

    val paint = Paint()
    paint.isAntiAlias = true

    val canvas = Canvas(output)
    canvas.drawARGB(0, 0, 0, 0)
    canvas.drawCircle(radius, radius, radius, paint)
    paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

    val rect = Rect(0, 0, length, length)
    canvas.drawBitmap(this, rect, rect, paint)
    return output
}