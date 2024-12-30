package me.sabapro.udemy_notetaking_app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.sabapro.udemy_notetaking_app.model.Note
import me.sabapro.udemy_notetaking_app.repository.NoteRepository

class NoteViewModel(
    app : Application,
    private val noteRepository: NoteRepository)
    :AndroidViewModel(app)
{

    fun addNote(note: Note) =
        viewModelScope.launch {
            noteRepository.insertNote(note)
        }

    fun deleteNote(note: Note){
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            noteRepository.updateNote(note)
        }
    }

    fun getAllNotes() = noteRepository.getAllNotes()

    fun searchNote(query : String?)  =
        noteRepository.searchNote(query)

}