package com.bmnmrls.yottings.notes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bmnmrls.domain.models.Note
import com.bmnmrls.yottings.R
import com.bmnmrls.yottings.databinding.NoteItemLayoutBinding
import com.bmnmrls.yottings.utils.ktx.setTextFromStringOrResource

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    lateinit var listener: ((Long) -> Unit)
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

        fun bind(item: Note, listener: ((Long) -> Unit)) = with(binding) {
            contentTextView.setTextFromStringOrResource(item.content)
            dateTextView.setTextFromStringOrResource(item.date)
            favoriteImageView.visibility = if (item.isFavorite) View.VISIBLE else View.GONE
            root.setOnClickListener { item.id?.let { itemId -> listener(itemId) } }
        }

    }

}