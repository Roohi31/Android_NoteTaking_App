package me.sabapro.udemy_notetaking_app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import me.sabapro.udemy_notetaking_app.MainActivity
import me.sabapro.udemy_notetaking_app.R
import me.sabapro.udemy_notetaking_app.adapter.NoteAdapter
import me.sabapro.udemy_notetaking_app.databinding.FragmentHomeBinding
import me.sabapro.udemy_notetaking_app.databinding.FragmentUpdateNoteBinding
import me.sabapro.udemy_notetaking_app.model.Note
import me.sabapro.udemy_notetaking_app.viewmodel.NoteViewModel

class UpdateNoteFragment : Fragment(R.layout.fragment_update_note) {

    private var _binding : FragmentUpdateNoteBinding?= null
    private val binding get() = _binding!!

    private lateinit var notesViewModel : NoteViewModel

    private lateinit var currentNote : Note

    // since the update note fragment contains argument in nav graph ve need to create
    private val args : UpdateNoteFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentUpdateNoteBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesViewModel = (activity as MainActivity).noteViewModel
        currentNote = args.note!!

        binding.etNoteTitleUpdate.setText(currentNote.noteTitle)
        binding.etNoteBodyUpdate.setText(currentNote.noteBody)

        // if the user update the note
        binding.fabDone.setOnClickListener{
            val title = binding.etNoteTitleUpdate.text.toString().trim()
            val body = binding.etNoteBodyUpdate.text.toString().trim()

            if(title.isNotEmpty())
            {
                val note = Note(currentNote.id,title,body)
                notesViewModel.updateNote(note)
                view.findNavController().navigate(R.id.action_updateNoteFragment_to_homeFragment)
            }
            else
            {
                Toast.makeText(
                    context,
                    "Please enter note Title",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    // alert dialog
    private fun deleteNote()
    {
        // error might come here 0.45 alert dialog
        AlertDialog.Builder(requireActivity()).apply {
            setTitle("Delete Note")
            setMessage("You want to delete this note")
            setPositiveButton("Delete"){_,_ ->
                notesViewModel.deleteNote(currentNote)
                view?.findNavController()?.navigate(
                    R.id.action_updateNoteFragment_to_homeFragment
                )
            }
            setNegativeButton("Cancel", null)
        }.create().show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_update_note,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId)
        {
            R.id.menu_delete -> {
                deleteNote()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}