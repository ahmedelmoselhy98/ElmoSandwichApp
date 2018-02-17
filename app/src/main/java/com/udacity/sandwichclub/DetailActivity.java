package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.w3c.dom.Text;

import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG ="DetailActivity";
    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private ImageView sandwitchImageIv;
    private TextView placeOfOriginTextView;
    private TextView alsoKnownAsTextView;
    private TextView descriptionTextView;
    private TextView ingredientsTextView;

    Sandwich sandwich;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        sandwitchImageIv = findViewById(R.id.image_iv);
        placeOfOriginTextView = findViewById(R.id.origin_tv);
        alsoKnownAsTextView = findViewById(R.id.also_known_tv);
        descriptionTextView = findViewById(R.id.description_tv);
        ingredientsTextView = findViewById(R.id.ingredients_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        setTitle(sandwich.getMainName());
        populateUI();

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        if (TextUtils.isEmpty(sandwich.getImage()));
        {
            Picasso.with(this)
                    .load("https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Grilled_ham_and_cheese_014.JPG/800px-Grilled_ham_and_cheese_014.JPG")
                    .into(sandwitchImageIv);
            Log.e(TAG, "Image Url\t" + sandwich.getImage());
        }
        List<String> alsoKnownAs = sandwich.getAlsoKnownAs();
        //Check if the Arrary is Empty
        if (alsoKnownAs.size() > 0){
        for (int i = 0;i<alsoKnownAs.size();i++){
            //Check if the Arrary Item is Empty
            if (!TextUtils.isEmpty(alsoKnownAs.get(i)))
            alsoKnownAsTextView.append(alsoKnownAs.get(i));
            Log.e(TAG,alsoKnownAs.get(i));
        }
        }else
            alsoKnownAsTextView.setText("-----");

        List<String> ingredients = sandwich.getIngredients();
        //Check if the Arrary is Empty
        if (ingredients.size()>0){
        for (int i = 0;i<ingredients.size();i++){
            //Check if the Arrary Item is Empty
            if (!TextUtils.isEmpty(ingredients.get(i)))
            ingredientsTextView.append(ingredients.get(i));
            Log.e(TAG,ingredients.get(i));
        }
        }else
            ingredientsTextView.setText("-----");

        if (!TextUtils.isEmpty(sandwich.getPlaceOfOrigin())){
            placeOfOriginTextView.setText(sandwich.getPlaceOfOrigin());
        }else placeOfOriginTextView.setText("-----");
        if (!TextUtils.isEmpty(sandwich.getDescription())){
            descriptionTextView.setText(sandwich.getDescription());
        }else descriptionTextView.setText("-----");

    }


}
