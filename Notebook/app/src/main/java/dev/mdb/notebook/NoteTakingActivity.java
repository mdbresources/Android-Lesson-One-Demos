package dev.mdb.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NoteTakingActivity extends AppCompatActivity {

    EditText txtNote;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_taking_activity);

        // Initialize UI elements using their ID's set in the editor layout.
        txtNote = findViewById(R.id.txtNote);
        btnSave = findViewById(R.id.btnSave);

        // Determine if we should use the existing saved note,
        // or start from scratch.
        boolean newNote = getIntent().getBooleanExtra("new_note", false);

        // If this should be a new note, then keep the
        // txtNote field empty, otherwise fill it with
        // the text saved in the current note file.
        if (!newNote) {
            // If there is no existing, then tell this to the user.
            if (FileUtils.fileExists("note.txt", this)) {
                String oldNoteData = FileUtils.readFromFile("note.txt", this);
                txtNote.setText(oldNoteData);
            } else {
                // Display a toast to the user that the existing
                // note.txt could not be found.
                Toast fileNotFound = Toast.makeText(getApplicationContext(), "Could not find old note.", Toast.LENGTH_SHORT);
                fileNotFound.show();

            }
        }

        // Set OnClickListener Behavior for buttons.
        // Implements the OnClickListener methods in-line.
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save the current text in the txtNote field into the note.txt file.
                String newNoteData = txtNote.getText().toString();
                FileUtils.writeToFile("note.txt", newNoteData, NoteTakingActivity.this);

                // Show a toast that the file was saved.
                Toast fileSaved = Toast.makeText(getApplicationContext(), "File Saved.", Toast.LENGTH_SHORT);
                fileSaved.show();
            }
        });
    }

}
