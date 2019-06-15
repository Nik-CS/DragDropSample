package se.wintren.quickdrop

import androidx.databinding.BindingAdapter
import se.wintren.quickdrop.view.PhotoFrameView
import se.wintren.quickdrop.viewmodel.PhotoManager

@BindingAdapter("photoManager")
fun PhotoFrameView.handleWith(manager: PhotoManager) {
    manager.add(this)
}