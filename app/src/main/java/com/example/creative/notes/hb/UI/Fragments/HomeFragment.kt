package com.example.creative.notes.hb.UI.Fragments

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.creative.notes.hb.R
import com.example.creative.notes.hb.UI.Adapter.NotesAdapter
import com.example.creative.notes.hb.ViewModel.NotesViewModel
import com.example.creative.notes.hb.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    private val viewModel: NotesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        viewModel.getAllNotes().observe(viewLifecycleOwner) { notesList ->
            // it yha pe notes list h iska naam b change kr skte h

            binding.recylerViewHome.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.recylerViewHome.adapter = NotesAdapter(requireContext(), notesList)
        }

        binding.clearBTN.setOnClickListener {

            viewModel.getLowNotes().observe(viewLifecycleOwner) { notesList ->
                // it yha pe notes list h iska naam b change kr skte h

                binding.recylerViewHome.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.recylerViewHome.adapter = NotesAdapter(requireContext(), notesList)
            }
        }

        binding.LowBTN.setOnClickListener {

            viewModel.getMediumNotes().observe(viewLifecycleOwner) { notesList ->
                // it yha pe notes list h iska naam b change kr skte h

                binding.recylerViewHome.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.recylerViewHome.adapter = NotesAdapter(requireContext(), notesList)
            }
        }

        binding.HIghBTN.setOnClickListener {

            viewModel.getHighNotes().observe(viewLifecycleOwner) { notesList ->
                // it yha pe notes list h iska naam b change kr skte h

                binding.recylerViewHome.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.recylerViewHome.adapter = NotesAdapter(requireContext(), notesList)
            }
        }
        binding.allNotes.setOnClickListener {
            viewModel.getAllNotes().observe(viewLifecycleOwner) { notesList ->
                // it yha pe notes list h iska naam b change kr skte h

                binding.recylerViewHome.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.recylerViewHome.adapter = NotesAdapter(requireContext(), notesList)
            }
        }


        binding.addNoteFAB.setOnClickListener {
            Navigation.findNavController(it!!)
                .navigate(R.id.action_homeFragment_to_createNoteFragment)
        }

        return binding.root
    }


}