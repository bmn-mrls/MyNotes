package com.bmnmrls.mynotes.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bmnmrls.mynotes.R
import com.bmnmrls.mynotes.databinding.NoteItemLayoutBinding
import com.bmnmrls.mynotes.home.models.NoteUI

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    lateinit var listener: ((Long) -> Unit)
    private val notes: MutableList<NoteUI> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.note_item_layout, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(notes[position], listener)

    override fun getItemCount(): Int = notes.size

    fun addNotes(notes: List<NoteUI>) {
        this.notes.clear()
        this.notes.addAll(notes)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding: NoteItemLayoutBinding = NoteItemLayoutBinding.bind(view)

        fun bind(item: NoteUI, listener: ((Long) -> Unit)) = with(binding) {
            contentTextView.text = item.content
            dateTextView.text = item.date
            root.setOnClickListener { listener(item.id) }
        }

    }

}