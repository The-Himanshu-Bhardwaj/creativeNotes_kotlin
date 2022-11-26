package com.example.creative.notes.hb.UI.Fragments

import android.os.Bundle
import android.provider.ContactsContract
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.creative.notes.hb.Models.NotesModel
import com.example.creative.notes.hb.R
import com.example.creative.notes.hb.ViewModel.NotesViewModel

import com.example.creative.notes.hb.databinding.FragmentCreateNoteBinding

import java.util.*

class CreateNoteFragment : Fragment() {

    lateinit var binding : FragmentCreateNoteBinding
    var priority : String = "1"

    val viewModel : NotesViewModel  by viewModels()
    //new code

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentCreateNoteBinding.inflate(layoutInflater, container, false)
        binding.greenDOT.setImageResource(R.drawable.done_icon)

        binding.doneFAB.setOnClickListener {
            createNote(it)
        }

        binding.greenDOT.setOnClickListener { greenDotClicked () }
        binding.redDOT.setOnClickListener { redDotClicked() }
        binding.yellowDOT.setOnClickListener { yellowDotClicked() }


        return binding.root
    }

    private fun yellowDotClicked() {
        binding.yellowDOT.setImageResource(R.drawable.done_icon)
        binding.redDOT.setImageResource(0)
        binding.greenDOT.setImageResource(0)

        priority = "2"

    }

    private fun redDotClicked() {
        binding.redDOT.setImageResource(R.drawable.done_icon)
        binding.yellowDOT.setImageResource(0)
        binding.greenDOT.setImageResource(0)
        priority="3"
    }

    private fun greenDotClicked() {
        binding.greenDOT.setImageResource(R.drawable.done_icon)
        binding.redDOT.setImageResource(0)
        binding.yellowDOT.setImageResource(0)
        priority="1"
    }

    private fun createNote(it: View?) {
        val title = binding.titleBox.text.toString()
        val subTitle = binding.subtitleBOX.text.toString()
        val noteText = binding.noteBOX.text.toString()
        // date

        val d = Date()
        val noteDate: String = DateFormat.format("MMMM d, yyyy", d).toString()

        val notePriority = priority

        val note = NotesModel(null, title, subTitle, noteText, noteDate, priority)

        viewModel.addNote(note)
        Toast.makeText(this.context, "Created !", Toast.LENGTH_LONG).show()

        Navigation.findNavController(it!!).navigate(R.id.action_createNoteFragment_to_homeFragment)






    }

}