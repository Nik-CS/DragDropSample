package se.wintren.quickdrop.viewmodel

import androidx.lifecycle.ViewModel

class MainViewModel(val photoManager: PhotoManager) : ViewModel() {

    fun setRandomPhoto(id: Int) {
        photoManager.setRandomPhoto(id)
    }

}


