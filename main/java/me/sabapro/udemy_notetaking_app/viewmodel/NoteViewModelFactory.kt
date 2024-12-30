package me.sabapro.udemy_notetaking_app.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.sabapro.udemy_notetaking_app.repository.NoteRepository

class NoteViewModelFactory(val app : Application,
    private val noteRepository: NoteRepository)
    : ViewModelProvider.Factory
{
    // Template only change return type
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteViewModel(app,noteRepository) as T
    }

}