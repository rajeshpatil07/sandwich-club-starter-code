package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    public static String mName="name";
    public static String mDescription="description";
    public static String mMainName="mainName";
    public static String mPlaceOfOrigin="placeOfOrigin";
    public static String mImageSrc="image";
    public static String mAlsoKnownAs="alsoKnownAs";
    public static String mIngredients="ingredients";

    public static Sandwich parseSandwichJson(String json) {
        JSONObject mainObj;
        JSONObject nameObj;
        Sandwich sandwich=new Sandwich();
        try {
            mainObj = new JSONObject(json);
            nameObj=mainObj.getJSONObject(mName);
            sandwich.setDescription(mainObj.getString(mDescription));
            sandwich.setMainName(nameObj.getString(mMainName));
            sandwich.setImage(mainObj.getString(mImageSrc));
            sandwich.setPlaceOfOrigin(mainObj.getString(mPlaceOfOrigin));
            JSONArray jsonArray = nameObj.getJSONArray(mAlsoKnownAs);
            List<String> alsoKnownAs = new ArrayList<String>();
            for(int i = 0; i < jsonArray.length(); i++){
                alsoKnownAs.add(jsonArray.getString(i));

            }
            sandwich.setAlsoKnownAs(alsoKnownAs);

            JSONArray jsonArrayingredients = mainObj.getJSONArray(mIngredients);
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
