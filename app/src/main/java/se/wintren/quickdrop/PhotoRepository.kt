package se.wintren.quickdrop

import android.content.Context
import android.graphics.Bitmap


class PhotoRepository(val context: Context) {

    fun getBitmap(photo: Photo?): Bitmap? {
        return context.getBitmapFromAssets(photo?.path ?: return null)
    }

}

