package com.example.creative.notes.hb.UI.Adapter

import android.content.Context
import android.net.wifi.hotspot2.pps.HomeSp
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.creative.notes.hb.Models.NotesModel
import com.example.creative.notes.hb.R
import com.example.creative.notes.hb.UI.Fragments.HomeFragment
import com.example.creative.notes.hb.UI.Fragments.HomeFragmentDirections
import com.example.creative.notes.hb.ViewModel.NotesViewModel
import com.example.creative.notes.hb.databinding.ItemNotesRecyclerBinding

class NotesAdapter(val requireContext: Context, val notes : List<NotesModel>) : RecyclerView.Adapter<NotesAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: ItemNotesRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
        var title = itemView.findViewById<TextView>(binding.noteTitle.id)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val view = ItemNotesRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = notes[position]
        holder.binding.noteTitle.text = data.title
        holder.binding.noteSubtitle.text = data.subtitle
        holder.binding.noteDescription.text = data.subtitle
        holder.binding.dateLabel.text = data.date

        when(data.priority) {
            "1" -> {holder.binding.priorityDOT.setBackgroundResource(R.drawable.green_icon)}
            "2" -> {holder.binding.priorityDOT.setBackgroundResource(R.drawable.yellow_icon)}
            "3" -> {holder.binding.priorityDOT.setBackgroundResource(R.drawable.red_icon)}
        }

        holder.binding.root.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(data)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
       return notes.size
    }
}