package com.example.myapplication.network;

import android.util.Log;

import com.example.myapplication.model.Category;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GetCategoryDeserializer implements JsonDeserializer<List<Category>> {
    @Override
    public List<Category> deserialize(
            JsonElement json,
            Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        List<Category> mItems = new ArrayList<>();
        JsonObject bodyObject = json.getAsJsonObject();
        JsonArray jsonArray = bodyObject.getAsJsonArray("categories");
        for (int i = 0; i <jsonArray.size() ; i++) {
            JsonObject category = jsonArray.get(i).getAsJsonObject();
            int id = category.get("id").getAsInt();
            String name = category.get("name").getAsString();
            String order = category.get("order").getAsString();

            Category item = new Category(id, name, order);
            mItems.add(item);
        }
        return mItems;


    }

}
