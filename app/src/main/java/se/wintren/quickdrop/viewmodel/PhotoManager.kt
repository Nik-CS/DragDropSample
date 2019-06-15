package se.wintren.quickdrop.viewmodel

import android.view.DragEvent
import android.view.View
import se.wintren.quickdrop.Photo
import se.wintren.quickdrop.PhotoRepository
import se.wintren.quickdrop.view.CircleDragShadowBuilder
import se.wintren.quickdrop.view.PhotoFrameView

class PhotoManager(private val repository: PhotoRepository) {

    private val map = mutableMapOf<Int, PhotoHolder>()

    fun setRandomPhoto(id: Int) {
        val photoHolder = map[id]
        val random = Photo.random()
        photoHolder?.photo = random
        photoHolder?.photoFrameView?.loadBitmap(repository.getBitmap(random))
    }

    fun add(photoFrameView: PhotoFrameView) {
        if (map.contains(photoFrameView.id)) {
            val holder = map[photoFrameView.id]!!
            holder.photoFrameView = photoFrameView
            photoFrameView.loadBitmap(repository.getBitmap(holder.photo))
        } else {
            map[photoFrameView.id] = PhotoHolder(photoFrameView)
        }
        photoFrameView.apply {
            setOnLongClickListener(::onLongClick)
            setOnDragListener(::onDrag)
        }
    }

    fun remove(photoFrameView: PhotoFrameView) {
        map.remove(photoFrameView.id)
    }

    private fun onLongClick(view: View): Boolean {
        val photoHolder = map[view.id]
        photoHolder?.photo?.let {
            val bitmap = repository.getBitmap(it) ?: return true
            val shadowBuilder = CircleDragShadowBuilder(view.context, bitmap)
            view.startDragAndDrop(null, shadowBuilder, view, View.DRAG_FLAG_OPAQUE)
        }
        return true
    }

    private fun onDrag(view: View, event: DragEvent): Boolean {
        when (event.action) {
            DragEvent.ACTION_DRAG_ENTERED -> {
                (view as PhotoFrameView).startHighlight()
            }
            DragEvent.ACTION_DRAG_EXITED, DragEvent.ACTION_DRAG_ENDED -> {
                (view as PhotoFrameView).endHighlight()
            }
            DragEvent.ACTION_DROP -> {
                val destinationView = view as PhotoFrameView
                val originView: PhotoFrameView = event.localState as PhotoFrameView

                val destinationHolder = map[destinationView.id]!!
                val originHolder = map[originView.id]!!

                val destinationPhoto = destinationHolder.photo
                destinationHolder.photo = originHolder.photo
                destinationView.loadBitmap(repository.getBitmap(destinationHolder.photo!!))

                originHolder.photo = destinationPhoto
                originView.loadBitmap(repository.getBitmap(originHolder.photo))
                return true
            }
            else -> {
                // Do nothing
            }
        }
        return true
    }

    class PhotoHolder(var photoFrameView: PhotoFrameView) {
        var photo: Photo? = null
    }

}