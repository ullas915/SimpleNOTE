package com.example.projectnote

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectnote.databinding.ActivityAddNoteBinding
import com.example.projectnote.databinding.ActivityMainBinding

class AddNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var db:NoteDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db= NoteDatabaseHelper(this)
        binding.save.setOnClickListener {
            val title=binding.titleEditText.text.toString()
            val content=binding.contentEditText.text.toString()
            val note=Note(0,title,content)
            db.insertNote(note)
            finish() //it is used to end the activity and come back to MainActivity
            Toast.makeText(this,"Note Saved",Toast.LENGTH_SHORT).show()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}