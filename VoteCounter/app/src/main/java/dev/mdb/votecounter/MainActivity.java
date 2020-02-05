package dev.mdb.votecounter;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView candidate1Photo;
    TextView candidate1Lbl;

    ImageView candidate2Photo;
    TextView candidate2Lbl;

    ImageView candidate3Photo;
    TextView candidate3Lbl;

    ImageView candidate4Photo;
    TextView candidate4Lbl;

    List<ImageView> candidatePhotos;
    List<TextView> candidateLabels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Candidate 1's UI Components.
        candidate1Photo = findViewById(R.id.candidate1Photo);
        candidate1Lbl = findViewById(R.id.candidate1Lablel);

        // Initialize Candidate 2's UI Components.
        candidate2Photo = findViewById(R.id.candidate2Photo);
        candidate2Lbl = findViewById(R.id.candidate2Lablel);

        // Initialize Candidate 3's UI Components.
        candidate3Photo = findViewById(R.id.candidate3Photo);
        candidate3Lbl = findViewById(R.id.candidate3Lablel);

        // Initialize Candidate 4's UI Components.
        candidate4Photo = findViewById(R.id.candidate4Photo);
        candidate4Lbl = findViewById(R.id.candidate4Lablel);

        // Put all UI Components into Lists to iterate through them later.
        candidatePhotos = Arrays.asList(candidate1Photo, candidate2Photo,
                                        candidate3Photo, candidate4Photo);
        candidateLabels = Arrays.asList(candidate1Lbl, candidate2Lbl,
                                        candidate3Lbl, candidate4Lbl);

        // Get the hashmap of vote counts from our Utils function countVotes.
        // Display a toast if an exception occurs.
        HashMap<String, Integer> votes = null;
        try {
            votes = Utils.countVotes(this);
        } catch (IOException e) {
            Toast errorToast = Toast.makeText(this, "Error counting votes.", Toast.LENGTH_LONG);
            errorToast.show();
        }

        // Count all the votes tallied.
        int totalVotes = 0;
        for (String candidate: votes.keySet()) {
            totalVotes += votes.get(candidate);
        }

        // Set the drawable and text for each candidates cell.
        int candidateIndex = candidatePhotos.size() - 1;
        for (String candidate: votes.keySet()) {
            // Get the vote count and percentage of the total votes for this candidate.
            int candidateVoteCount = votes.get(candidate);
            float percentOfVote = candidateVoteCount / (float) totalVotes * 100;

            // Get the proper UI components from the arrays.
            ImageView candidateImage = candidatePhotos.get(candidateIndex);
            TextView candidateLabel = candidateLabels.get(candidateIndex);

            // Set the image of the ImageView based of the candidate's name.
            String imageFileName = Utils.getFileNameVersion(candidate);
            candidateImage.setImageDrawable(Utils.getImage(this, imageFileName));

            // Construct a string for the candidate's label. This is based of a placeholder in the
            // res/values/string.xml file.
            String labelText = getString(R.string.candidate_label, candidate, candidateVoteCount, percentOfVote);
            candidateLabel.setText(labelText);

            candidateIndex -= 1;
        }
    }
}
