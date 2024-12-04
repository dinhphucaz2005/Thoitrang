package com.example.thoitrang.database;

import androidx.room.TypeConverter;

import com.example.thoitrang.dto.ItemDto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Converter {

    private static final Gson gson = new Gson();

    @TypeConverter
    public static String fromListToJson(List<ItemDto> list) {
        return gson.toJson(list);
    }

    @TypeConverter
    public static List<ItemDto> fromJsonToList(String json) {
        Type type = new TypeToken<List<ItemDto>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

}
