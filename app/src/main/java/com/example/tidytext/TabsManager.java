package com.example.tidytext;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TabsManager {

    private static final String PREF_NAME = "tabs_prefs";
    private static final String KEY_TABS = "tabs_list";

    public static void saveTabs(Context context, List<CustomTab> tabs) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(tabs);
        editor.putString(KEY_TABS, json);
        editor.apply();
    }

    public static List<CustomTab> loadTabs(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(KEY_TABS, null);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<CustomTab>>() {}.getType();
        List<CustomTab> tabs = gson.fromJson(json, type);

        if (tabs == null) {
            tabs = new ArrayList<>();
            tabs.add(new CustomTab("All", "", true));
            tabs.add(new CustomTab("Personal", "", true));
            tabs.add(new CustomTab("Govt", "", true));
            tabs.add(new CustomTab("Bookings", "", true));
            tabs.add(new CustomTab("Recharge", "", true));
            tabs.add(new CustomTab("Transactions", "", true));
            tabs.add(new CustomTab("Spam", "", true));
        }

        return tabs;
    }
}
