package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        JSONObject mainObj;
        JSONObject nameObj;
        Sandwich sandwich=new Sandwich();
        try {
            mainObj = new JSONObject(json);
            nameObj=mainObj.getJSONObject("name");
            sandwich.setDescription(mainObj.getString("description"));
            sandwich.setMainName(nameObj.getString("mainName"));
            sandwich.setImage(mainObj.getString("image"));
            sandwich.setPlaceOfOrigin(mainObj.getString("placeOfOrigin"));
            JSONArray jsonArray = nameObj.getJSONArray("alsoKnownAs");
            List<String> alsoKnownAs = new ArrayList<String>();
            for(int i = 0; i < jsonArray.length(); i++){
                alsoKnownAs.add(jsonArray.getString(i));

            }
            sandwich.setAlsoKnownAs(alsoKnownAs);

            JSONArray jsonArrayingredients = mainObj.getJSONArray("ingredients");
            List<String> ingredients = new ArrayList<String>();
            for(int i = 0; i < jsonArrayingredients.length(); i++){
                ingredients.add(jsonArrayingredients.getString(i));

            }
            sandwich.setIngredients(ingredients);

        }catch (Exception e){
            e.printStackTrace();
        }



        return sandwich;
    }
}
