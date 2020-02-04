package dev.mdb.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnNewNote;
    Button btnOldNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize buttons using ID's given in layout editor.
        btnNewNote = findViewById(R.id.btnNewNote);
        btnOldNote = findViewById(R.id.btnOldNote);

        // Set OnClickListener Behavior for buttons.
        // Implements the OnClickListener methods in the class.
        btnNewNote.setOnClickListener(this);
        btnOldNote.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Define the behavior of the onClick depending on the view that was clicked.
        switch (v.getId()) {
            case R.id.btnNewNote:
                // Create a new intent to the NoteTakingActivity and
                // put an extra into the intent to denote that this
                // is a new note.
                Intent newNoteIntent = new Intent(this, NoteTakingActivity.class);
                newNoteIntent.putExtra("new_note", true);
                this.startActivity(newNoteIntent);
                break;
            case R.id.btnOldNote:
                // Create a new intent to the NoteTakingActivity and
                // put an extra into the intent to denote that this
                // should use the old note data.
                Intent oldNoteIntent = new Intent(this, NoteTakingActivity.class);
                oldNoteIntent.putExtra("new_note", false);
                this.startActivity(oldNoteIntent);
                break;
            default:
                break;
        }
    }
}
