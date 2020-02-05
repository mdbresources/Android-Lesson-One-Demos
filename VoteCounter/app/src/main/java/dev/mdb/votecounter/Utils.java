package dev.mdb.votecounter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

class Utils {

    /** Count all the votes in the votes.txt file. Return a hashmap with
     *  each candidates name mapping to their total vote count.*/
    public static HashMap<String, Integer> countVotes(Context c) throws IOException {
        HashMap<String, Integer> votes = new HashMap<>();
        AssetManager assetManager = c.getAssets();
        InputStream is = assetManager.open("votes.txt");
        Scanner voteScanner = new Scanner(is);
        while (voteScanner.hasNextLine()) {
            String candidateName = voteScanner.nextLine();
            if (!votes.containsKey(candidateName)) {
                votes.put(candidateName, 0);
            }
            int currentVotes = votes.get(candidateName);
            votes.replace(candidateName, currentVotes + 1);
        }
        return votes;
    }

    /** Return the equivalent file name from a person's name. This can
     *  be done by putting it in lower case and removing all spaces.*/
    public static String getFileNameVersion(String name) {
        return name.toLowerCase().replace(" ", "");
    }

    /** Get the Drawable from the image's name.*/
    public static Drawable getImage(Context c, String imageName) {
        int resourceID = c.getResources().getIdentifier(imageName, "raw", c.getPackageName());
        return c.getResources().getDrawable(resourceID);
    }

}
