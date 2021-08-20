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

public class getArticlesDeserialize implements JsonDeserializer<List<Item>> {
    @Override
    public List<Item> deserialize(
            JsonElement json,
            Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {

        List<Item> mItems = new ArrayList<>();
        JsonObject bodyObject = json.getAsJsonObject();
        JsonArray articleArray = bodyObject.getAsJsonArray("articles");
        for (int i = 0; i <articleArray.size() ; i++) {
            JsonObject itemArray = articleArray.get(i).getAsJsonObject();

            if (!itemArray.has("image"))
                continue;

            int id = itemArray.get("id").getAsInt();
            String title = itemArray.get("title").getAsString();
            String slug = itemArray.get("slug").getAsString();
            String desc = itemArray.get("description").getAsString();
            String image = itemArray.get("image").getAsString();
            String date = itemArray.get("created_at").getAsString();


        }
        return mItems;
    }
}
