package com.bmnmrls.yottings.notes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bmnmrls.domain.models.Note
import com.bmnmrls.yottings.R
import com.bmnmrls.yottings.databinding.NoteItemLayoutBinding
import javax.inject.Inject

class NotesAdapter @Inject constructor() : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    lateinit var listener: ((Note) -> Unit)
    private val notes: MutableList<Note> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.note_item_layout, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(notes[position], listener)

    override fun getItemCount(): Int = notes.size

    fun addNotes(notes: List<Note>) {
        this.notes.clear()
        this.notes.addAll(notes)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding: NoteItemLayoutBinding = NoteItemLayoutBinding.bind(view)

        fun bind(item: Note, listener: ((Note) -> Unit)) = with(binding) {
            note = item
            this.listener = View.OnClickListener { listener(item) }
            executePendingBindings()
        }

    }

}