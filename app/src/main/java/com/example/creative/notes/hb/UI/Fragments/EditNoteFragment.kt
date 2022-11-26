package com.example.creative.notes.hb.UI.Fragments

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.text.format.DateFormat
import android.view.*
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.creative.notes.hb.Models.NotesModel
import com.example.creative.notes.hb.R
import com.example.creative.notes.hb.ViewModel.NotesViewModel
import com.example.creative.notes.hb.databinding.FragmentEditNoteBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*

class EditNoteFragment : Fragment() {

    val notes by navArgs<EditNoteFragmentArgs>()
    private var priority = "1"

    private lateinit var binding : FragmentEditNoteBinding
    private val viewModel : NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentEditNoteBinding.inflate(layoutInflater, container, false)



        // getting data
        binding.titleBox.setText(notes.data.title)
        binding.subtitleBOX.setText(notes.data.subtitle)
        binding.noteBOX.setText(notes.data.note)

        when(notes.data.priority) {
            "1" -> {
                priority = "1"
                binding.greenDOT.setImageResource(R.drawable.done_icon)
                binding.redDOT.setImageResource(0)
                binding.yellowDOT.setImageResource(0)
            }
            "2" -> {
                priority = "2"
                binding.yellowDOT.setImageResource(R.drawable.done_icon)
                binding.redDOT.setImageResource(0)
                binding.greenDOT.setImageResource(0)}
            "3" -> {
                priority = "3"
                binding.redDOT.setImageResource(R.drawable.done_icon)
                binding.yellowDOT.setImageResource(0)
                binding.greenDOT.setImageResource(0)}
        }

        binding.greenDOT.setOnClickListener { greenDotClicked () }
        binding.redDOT.setOnClickListener { redDotClicked() }
        binding.yellowDOT.setOnClickListener { yellowDotClicked() }



        binding.doneFAB.setOnClickListener {
            val title = binding.titleBox.text.toString()
            val subTitle = binding.subtitleBOX.text.toString()
            val noteText = binding.noteBOX.text.toString()
            val id = notes.data.id
            // date

            val d = Date()
            val noteDate: String = DateFormat.format("MMMM d, yyyy", d).toString()

            val notePriority = priority

            val note = NotesModel(id, title, subTitle, noteText, noteDate, priority)

            viewModel.updateNote(note)
            Toast.makeText(this.context, "Updated !", Toast.LENGTH_LONG).show()

            Navigation.findNavController(it!!).navigate(R.id.action_editNoteFragment_to_homeFragment)
        }

        setHasOptionsMenu(true)

        // delete btn

        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.delete_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)


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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.deleteBTN) {

            val btmSheet = BottomSheetDialog(requireContext())
            btmSheet.setContentView(R.layout.delete_bottom_sheet)
            btmSheet.show()

            val yesBTN = btmSheet.findViewById<TextView>(R.id.yesButton)
            val noBtn = btmSheet.findViewById<TextView>(R.id.noBUtton)

            yesBTN?.setOnClickListener {
                viewModel.deleteNote(notes.data.id!!.toInt())
                Toast.makeText(requireContext(), "note deleted !", Toast.LENGTH_LONG)
                btmSheet.dismiss()
                Navigation.findNavController(it).navigate(R.id.action_editNoteFragment_to_homeFragment)


            }

            noBtn?.setOnClickListener { btmSheet.dismiss() }

        }
        return super.onOptionsItemSelected(item)
    }


}