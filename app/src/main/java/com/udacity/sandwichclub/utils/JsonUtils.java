package com.udacity.sandwichclub.utils;

import android.util.Log;
import android.widget.Toast;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final String TAG ="JsonUtils";
    public static Sandwich parseSandwichJson(String json) {
        String mainName;
        List<String> alsoKnownAs = new ArrayList<String>();
        String placeOfOrigin;
        String description;
        String image;
        List<String> ingredients = new ArrayList<String>();
        try {
            Sandwich sandwich;
            JSONObject root = new JSONObject(json);
            JSONObject nameJsonObject = root.getJSONObject("name");
            //getting Main Name for sandwitch
            mainName = nameJsonObject.getString("mainName");
            Log.e(TAG,mainName);
            //getting Json Array for asloKnownAs From json String
            JSONArray alsoKnownAsJsonArray = nameJsonObject.getJSONArray("alsoKnownAs");
            //Convert JsonArray of alsoKnownAs to List of String for alsoKnownAs
            for (int i = 0;i<alsoKnownAsJsonArray.length();i++){
             alsoKnownAs.add(alsoKnownAsJsonArray.getString(i));
                Log.e(TAG,alsoKnownAs.get(i));
            }
            //getting Place Of Origin for sandwitch
            placeOfOrigin = root.getString("placeOfOrigin");
            Log.e(TAG,placeOfOrigin);
            //getting description for sandwitch
            description = root.getString("description");
            Log.e(TAG,description);
            //getting image for sandwitch
            image = root.getString("image");
            Log.e(TAG,image);
            //getting Json Array for ingredients From json String
            JSONArray ingredientsJsonArray = root.getJSONArray("ingredients");
            //Convert JsonArray of ingredients to List of String for ingredients
            for (int i = 0;i<ingredientsJsonArray.length();i++){
                ingredients.add(ingredientsJsonArray.getString(i));
                Log.e(TAG,ingredients.get(i));
            }
            sandwich = new Sandwich(mainName,alsoKnownAs,placeOfOrigin,description,image,ingredients);
            return sandwich;
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG,"Faind In Parsing Json Data");
            Log.e(TAG,"Error\t"+e.getMessage());
            return null;
        }

    }
}
