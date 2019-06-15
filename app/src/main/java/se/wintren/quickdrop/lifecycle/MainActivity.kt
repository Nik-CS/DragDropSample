package se.wintren.quickdrop.lifecycle

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import se.wintren.quickdrop.PhotoRepository
import se.wintren.quickdrop.R
import se.wintren.quickdrop.databinding.ActivityMainBinding
import se.wintren.quickdrop.viewmodel.MainViewModel
import se.wintren.quickdrop.viewmodel.MainViewModelFactory
import se.wintren.quickdrop.viewmodel.PhotoManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // No injection for this small app
        val repository = PhotoRepository(this)
        val photoManager = PhotoManager(repository)
        val factory = MainViewModelFactory(photoManager)

        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        binding.viewModel = viewModel
    }

    override fun onStart() {
        super.onStart()
        photo1.setOnClickListener(::randomPhoto)
        photo2.setOnClickListener(::randomPhoto)
    }

    private fun randomPhoto(view: View) {
        viewModel.setRandomPhoto(view.id)
    }

}
