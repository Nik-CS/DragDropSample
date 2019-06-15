package se.wintren.quickdrop.view

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import se.wintren.quickdrop.R


class PhotoFrameView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    var bitmap: Bitmap? = null

    private val imageView: ImageView
        get() = findViewById(R.id.photoFrameImage)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_photo_frame, this, true)
    }

    fun loadBitmap(bitmap: Bitmap?) {
        this.bitmap = bitmap
        if (bitmap != null) {
            Glide.with(context)
                .load(bitmap)
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.color.white)
                .into(imageView)
        } else {
            imageView.setImageDrawable(context.getDrawable(R.drawable.empty_photo_frame))
        }
    }

    fun startHighlight() {
        val color = resources.getColor(R.color.colorAccent, null)
        setBackgroundColor(color)
    }

    fun endHighlight() {
        val color = resources.getColor(R.color.white, null)
        setBackgroundColor(color)
    }


}

