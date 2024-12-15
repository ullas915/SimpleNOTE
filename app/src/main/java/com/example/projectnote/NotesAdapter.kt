package com.example.projectnote

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(private var notes:List<Note>,context: Context):RecyclerView.Adapter<NotesAdapter.NoteViewHolder>(){

    private val db:NoteDatabaseHelper=NoteDatabaseHelper(context)

    class NoteViewHolder(itemView: View ):RecyclerView.ViewHolder(itemView){
        val block:LinearLayout=itemView.findViewById(R.id.block)
        val titleTextView: TextView =itemView.findViewById(R.id.titleTextView)
        val contentTextView:TextView=itemView.findViewById(R.id.contentTextView)
        val updateButton:ImageView=itemView.findViewById(R.id.updateButton)
       val deleteButton:ImageView=itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapter.NoteViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesAdapter.NoteViewHolder, position: Int) {
        val note=notes[position]
        holder.titleTextView.text=note.title
        holder.contentTextView.text=note.content

        holder.block.setOnClickListener {
            val intent=Intent(holder.itemView.context,UpdateNoteActivity::class.java).apply {
                putExtra("note_id",note.id)

            }
            holder.itemView.context.startActivity(intent)
            Toast.makeText(holder.itemView.context,"Recycle",Toast.LENGTH_SHORT).show()

        }

        holder.deleteButton.setOnClickListener {
            db.deleteNote(note.id)
            refreshData(db.getAllNotes())
            Toast.makeText(holder.itemView.context,"Deleted",Toast.LENGTH_SHORT).show()
        }

        holder.updateButton.setOnClickListener {
            val intent=Intent(holder.itemView.context,UpdateNoteActivity::class.java).apply {
                putExtra("note_id",note.id)

            }
            holder.itemView.context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int =notes.size

    fun refreshData(newNotes:List<Note>){
        notes=newNotes
        notifyDataSetChanged()
    }

}