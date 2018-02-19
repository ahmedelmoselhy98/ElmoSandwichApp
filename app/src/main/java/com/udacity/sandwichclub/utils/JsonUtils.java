package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final String TAG ="JsonUtils";
    public static Sandwich parseSandwichJson(String json) {

        // SUGGESTION 1---> make use of Strings as keys
        final String MAIN_NAME;
        final List<String> ALSO_KNOWN_AS = new ArrayList<>();
        final String PLACE_OF_ORIGIN;
        final String DESCRIPTION;
        final String IMAGE;
        final List<String> INGREDIENTS = new ArrayList<>();

        try {
            Sandwich sandwich = new Sandwich();
            JSONObject root = new JSONObject(json);

            // SUGGESTION---? 2,3  use Check the Existence of the data & use optString
            if (root.has("name")) { // chenk the name node is exist
                JSONObject nameJsonObject = root.getJSONObject("name");
                //getting Main Name for sandwitch
                if (nameJsonObject.has("mainName")){ // chenk the mainname node is exist
                MAIN_NAME = nameJsonObject.optString("mainName");
                sandwich.setMainName(MAIN_NAME);
                Log.e(TAG, MAIN_NAME);
                }
                //getting Json Array for asloKnownAs From json String
                if (nameJsonObject.has("alsoKnownAs")){ // chenk the asloKnownAs node is exist
                    JSONArray alsoKnownAsJsonArray = nameJsonObject.optJSONArray("alsoKnownAs");
                //Convert JsonArray of ALSO_KNOWN_AS to List of String for ALSO_KNOWN_AS
                for (int i = 0; i < alsoKnownAsJsonArray.length(); i++) {
                    ALSO_KNOWN_AS.add(alsoKnownAsJsonArray.optString(i));
                    Log.e(TAG, ALSO_KNOWN_AS.get(i));
                }
                sandwich.setAlsoKnownAs(ALSO_KNOWN_AS);
                }
                //getting Place Of Origin for sandwitch
                if (root.has("placeOfOrigin")) { // chenk the placeOfOrigin node is exist
                    PLACE_OF_ORIGIN = root.optString("placeOfOrigin");
                    Log.e(TAG, PLACE_OF_ORIGIN);
                    sandwich.setPlaceOfOrigin(PLACE_OF_ORIGIN);
                }
                //getting DESCRIPTION for sandwitch
                if (root.has("decription")) { // chenk the decription node is exist
                    DESCRIPTION = root.optString("decription");
                    Log.e(TAG, DESCRIPTION);
                    sandwich.setDescription(DESCRIPTION);
                }
                //getting IMAGE for sandwitch
                if (root.has("image")) { // chenk the image node is exist
                    IMAGE = root.optString("image");
                    Log.e(TAG, IMAGE);
                    sandwich.setImage(IMAGE);
                }
                //getting Json Array for INGREDIENTS From json String
                if (root.has("ingredients")) { // chenk the ingredients node is exist
                    JSONArray ingredientsJsonArray = root.optJSONArray("ingredients");
                //Convert JsonArray of INGREDIENTS to List of String for INGREDIENTS
                for (int i = 0; i < ingredientsJsonArray.length(); i++) {
                    INGREDIENTS.add(ingredientsJsonArray.optString(i));
                    Log.e(TAG, INGREDIENTS.get(i));
                }
                sandwich.setIngredients(INGREDIENTS);
                }
                return sandwich;
            }
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG,"Faind In Parsing Json Data");
            Log.e(TAG,"Error\t"+e.getMessage());
            return null;
        }

    }
}
