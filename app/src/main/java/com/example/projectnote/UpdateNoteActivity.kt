package com.example.projectnote

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectnote.databinding.ActivityUpdateNoteBinding

class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateNoteBinding
    private lateinit var db:NoteDatabaseHelper
    private var noteId:Int=-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db=NoteDatabaseHelper(this)

        noteId=intent.getIntExtra("note_id",-1)
        if(noteId==-1){
            finish()
            return
        }

        val note=db.getNoteByID(noteId)
        binding.updatetitleEditText.setText(note.title)
        binding.updatecontentEditText.setText(note.content)

        binding.updateSaveButton.setOnClickListener {
            val newTitle=binding.updatetitleEditText.text.toString()
            val newContent=binding.updatecontentEditText.text.toString()
            val updatedNote=Note(noteId,newTitle,newContent)
            db.updateNote(updatedNote)
            finish()
            Toast.makeText(this,"Changes saved",Toast.LENGTH_SHORT).show()

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}