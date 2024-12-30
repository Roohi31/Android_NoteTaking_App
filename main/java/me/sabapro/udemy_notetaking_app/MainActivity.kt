package me.sabapro.udemy_notetaking_app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import me.sabapro.udemy_notetaking_app.database.NoteDatabase
import me.sabapro.udemy_notetaking_app.databinding.ActivityMainBinding
import me.sabapro.udemy_notetaking_app.repository.NoteRepository
import me.sabapro.udemy_notetaking_app.viewmodel.NoteViewModel
import me.sabapro.udemy_notetaking_app.viewmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var noteViewModel : NoteViewModel

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setUpViewModel()

    }

    private fun setUpViewModel() {
        val noteRepository = NoteRepository(NoteDatabase(this))
        val viewModelProviderFactory = NoteViewModelFactory(application,
            noteRepository)
        noteViewModel = ViewModelProvider(this,
            viewModelProviderFactory).get(NoteViewModel::class.java)
    }

}