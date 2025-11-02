package com.example.tidytext;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    private ListView listViewSections;
    private EditText searchBar;
    private LinearLayout detailsContainer;
    private ArrayAdapter<String> adapter;
    private List<String> sections;

    // use CustomTab (consistent with TabsManager / TabsAdapter)
    private List<CustomTab> tabsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        listViewSections = findViewById(R.id.listViewSections);
        searchBar = findViewById(R.id.searchBar);
        detailsContainer = findViewById(R.id.detailsContainer);

        sections = Arrays.asList("Tabs", "Summary", "Notifications", "Themes", "Privacy",
                "Insights Dashboard", "Export & Integration", "Gamified Inbox");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sections);
        listViewSections.setAdapter(adapter);

        listViewSections.setOnItemClickListener((parent, view, position, id) -> {
            showSectionDetails(sections.get(position));
        });

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }
        });
    }

    private void showSectionDetails(String section) {
        detailsContainer.removeAllViews();

        switch (section) {
            case "Tabs":
                addSettingTitle("Tabs");
                addSettingText("Manage message categories and their rules.");

                // load tabs (CustomTab)
                tabsList = TabsManager.loadTabs(this);

                // RecyclerView for tabs
                RecyclerView recycler = new RecyclerView(this);
                recycler.setLayoutParams(new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                recycler.setLayoutManager(new LinearLayoutManager(this));

                TabsAdapter tabsAdapter = new TabsAdapter(this, tabsList);
                recycler.setAdapter(tabsAdapter);
                detailsContainer.addView(recycler);

                // Add new tab button
                Button addButton = new Button(this);
                addButton.setText("+ Add New Tab");
                addButton.setBackgroundColor(Color.parseColor("#2196F3"));
                addButton.setTextColor(Color.WHITE);
                addButton.setOnClickListener(v -> showAddTabDialog(tabsAdapter));
                detailsContainer.addView(addButton);
                break;

            case "Summary":
                addSettingTitle("Summary Settings");
                addSettingText("Control which messages are summarized.");
                addSettingToggle("Include Personal Messages", false);
                addSettingToggle("Send Priority Notification", true);
                break;

            case "Notifications":
                addSettingTitle("Notifications");
                addSettingText("Control notification timing and alerts.");
                addSettingToggle("Daily Summary Reminder", true);
                addSettingToggle("Offer Alerts", false);
                break;

            case "Themes":
                addSettingTitle("Themes");
                addSettingText("Change app appearance. Options: Light, Dark, or System Default.");
                break;

            case "Privacy":
                addSettingTitle("Privacy");
                addSettingText("Choose your preferred data mode.");
                addSettingToggle("Use Cloud AI (better accuracy)", true);
                addSettingToggle("Private Mode (local only)", false);
                break;

            case "Insights Dashboard":
                addSettingTitle("📊 Insights Dashboard");
                addSettingText("Visualize message trends and activity over time.");
                addFeatureButton("Open Insights Dashboard", InsightsActivity.class);
                break;

            case "Export & Integration":
                addSettingTitle("📤 Export & Integration");
                addSettingText("Export summaries or integrate with other apps.");
                addFeatureButton("Open Export Center", ExportActivity.class);
                break;

            case "Gamified Inbox":
                addSettingTitle("🎯 Gamified Inbox Clean-Up");
                addSettingText("Track your inbox score and earn achievements.");
                addFeatureButton("Open Gamified Inbox", GamifyActivity.class);
                break;

            default:
                addSettingText("Select a section to view its details.");
        }
    }

    // Show dialog to add a new tab; refresh adapter after adding
    private void showAddTabDialog(TabsAdapter adapter) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add New Tab");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(24, 16, 24, 0);

        final EditText inputName = new EditText(this);
        inputName.setHint("Tab name (e.g., Bills)");
        layout.addView(inputName);

        final EditText inputRules = new EditText(this);
        inputRules.setHint("Rules (comma-separated keywords)");
        layout.addView(inputRules);

        builder.setView(layout);

        builder.setPositiveButton("Add", (dialog, which) -> {
            String name = inputName.getText().toString().trim();
            String rules = inputRules.getText().toString().trim();
            if (!name.isEmpty()) {
                if (tabsList == null) tabsList = TabsManager.loadTabs(this);
                tabsList.add(new CustomTab(name, rules, false));
                TabsManager.saveTabs(this, tabsList);
                adapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    // Show dialog to edit/delete a tab
    // NOTE: adapter refresh is handled by saving + re-loading in this simplified flow
    private void showEditTabDialog(CustomTab tab) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Tab: " + tab.getName());

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(24, 16, 24, 0);

        final EditText inputRules = new EditText(this);
        inputRules.setText(tab.getRule());
        inputRules.setHint("Rules (comma-separated)");
        layout.addView(inputRules);

        builder.setView(layout);

        builder.setPositiveButton("Save", (dialog, which) -> {
            tab.setRule(inputRules.getText().toString().trim());
            TabsManager.saveTabs(this, tabsList);
            // Refresh view by reloading section
            showSectionDetails("Tabs");
        });

        builder.setNeutralButton("Delete", (dialog, which) -> {
            tabsList.remove(tab);
            TabsManager.saveTabs(this, tabsList);
            showSectionDetails("Tabs");
        });

        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    // Helper UI builders (kept as you had them)
    private void addSettingTitle(String title) {
        TextView tv = new TextView(this);
        tv.setText(title);
        tv.setTextSize(18);
        tv.setTypeface(null, Typeface.BOLD);
        tv.setTextColor(Color.BLACK);
        tv.setPadding(0, 10, 0, 10);
        detailsContainer.addView(tv);
    }

    private void addSettingText(String text) {
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setTextSize(15);
        tv.setTextColor(Color.DKGRAY);
        tv.setPadding(0, 0, 0, 10);
        detailsContainer.addView(tv);
    }

    private void addSettingToggle(String label, boolean defaultValue) {
        Switch sw = new Switch(this);
        sw.setText(label);
        sw.setChecked(defaultValue);
        sw.setPadding(0, 10, 0, 10);
        detailsContainer.addView(sw);
    }

    private void addFeatureButton(String label, Class<?> targetActivity) {
        Button btn = new Button(this);
        btn.setText(label);
        btn.setAllCaps(false);
        btn.setBackgroundColor(Color.parseColor("#2196F3"));
        btn.setTextColor(Color.WHITE);
        btn.setPadding(0, 20, 0, 20);
        btn.setOnClickListener(v -> {
            Intent intent = new Intent(SettingsActivity.this, targetActivity);
            startActivity(intent);
        });
        detailsContainer.addView(btn);
    }
}
