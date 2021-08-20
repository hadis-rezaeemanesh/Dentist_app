package com.example.myapplication.network;

import com.example.myapplication.model.modelTest.Item;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GetRelatedArticlesDeserializer implements JsonDeserializer<Item> {

    private static final String TAG = "GetRelatedArticlesDeserializer";

    @Override
    public Item deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        JsonObject bodyObject = json.getAsJsonObject();

        int id = bodyObject.get("id").getAsInt();
        String title = bodyObject.get("title").getAsString();
        String desc = bodyObject.get("description").getAsString();
        String image = bodyObject.get("image").getAsString();
        String date = bodyObject.get("created_at").getAsString();
        JsonArray articles = bodyObject.get("related_articles").getAsJsonArray();
        List<Item> items = new ArrayList<>();
        for (int j = 0; j <articles.size() ; j++) {
            JsonObject itemArray = articles.get(j).getAsJsonObject();
            int mId = itemArray.get("id").getAsInt();
            String mTitle = itemArray.get("title").getAsString();
            String mSlug = itemArray.get("slug").getAsString();
            String mDesc = itemArray.get("description").getAsString();
            String mImage = itemArray.get("image").getAsString();
            String mDate = itemArray.get("created_at").getAsString();


        }

        return null;

    }
}
