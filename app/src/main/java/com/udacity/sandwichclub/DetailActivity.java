package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private TextView tvDescription;
    private TextView tvPlaceOfOrigin;
    private TextView tvIngredients;
    private TextView tvAlso_known;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        tvDescription = findViewById(R.id. description_tv );
        tvPlaceOfOrigin = findViewById(R.id.placeorigin);
        tvIngredients = findViewById(R.id.ingredients_tv);
        tvAlso_known = findViewById(R.id.also_known_tv);

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
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .placeholder(R.drawable.user_placeholder)
                .error(R.drawable.user_placeholder_error)
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        tvDescription.setText(sandwich.getDescription());
        tvPlaceOfOrigin.setText(sandwich.getPlaceOfOrigin());
        String ing = "";
        try {
            for (int i = 0; sandwich.getIngredients().size() > i; i++) {
                ing += sandwich.getIngredients().get(i) + ", ";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        tvIngredients.setText(ing);
        String asyknow = "";
        for (int i = 0; sandwich.getAlsoKnownAs().size() > i; i++) {
            if(sandwich.getAlsoKnownAs().size()>0)
                asyknow += sandwich.getAlsoKnownAs().get(i) + ", ";
            else
                asyknow += sandwich.getAlsoKnownAs().get(i);
        }

        tvAlso_known.setText(asyknow);
    }
}
