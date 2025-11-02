package com.example.tidytext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerMessages;
    private MessageAdapter adapter;
    private List<Message> allMessages;
    private List<Message> filteredMessages;
    private TabLayout tabLayout;
    private EditText searchBar;
    private Button btnSummarize;
    private ImageButton btnSettings;

    private List<CustomTab> allTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerMessages = findViewById(R.id.recyclerMessages);
        recyclerMessages.setLayoutManager(new LinearLayoutManager(this));

        tabLayout = findViewById(R.id.tabLayout);
        searchBar = findViewById(R.id.searchBar);
        btnSummarize = findViewById(R.id.btnSummarize);
        btnSettings = findViewById(R.id.btnSettings);

        // Load tabs
        allTabs = TabsManager.loadTabs(this);
        setupTabs();

        allMessages = DemoMessagesProvider.getSampleMessages(); // replace with your message list
        filteredMessages = new ArrayList<>(allMessages);

        adapter = new MessageAdapter(filteredMessages);
        recyclerMessages.setAdapter(adapter);

        // Tab selection logic
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                filterByCategory(tab.getText().toString());
            }

            @Override public void onTabUnselected(TabLayout.Tab tab) {}
            @Override public void onTabReselected(TabLayout.Tab tab) {}
        });

        // Search bar logic
        searchBar.addTextChangedListener(new android.text.TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(android.text.Editable s) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterMessages(s.toString());
            }
        });

        // Buttons
        btnSummarize.setOnClickListener(v -> startActivity(new Intent(this, Summarize.class)));
        btnSettings.setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));

        // Bottom Nav
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_messages) return true;
            if (id == R.id.nav_insights) { startActivity(new Intent(this, InsightsActivity.class)); return true; }
            if (id == R.id.nav_export) { startActivity(new Intent(this, ExportActivity.class)); return true; }
            if (id == R.id.nav_gamify) { startActivity(new Intent(this, GamifyActivity.class)); return true; }
            return false;
        });
    }

    private void setupTabs() {
        tabLayout.removeAllTabs();
        for (CustomTab t : allTabs) {
            tabLayout.addTab(tabLayout.newTab().setText(t.getName()));
        }
    }

    private void filterMessages(String query) {
        filteredMessages.clear();
        for (Message msg : allMessages) {
            if (msg.getTitle().toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT)) ||
                    msg.getBody().toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT))) {
                filteredMessages.add(msg);
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void filterByCategory(String category) {
        filteredMessages.clear();

        if (category.equalsIgnoreCase("All")) {
            filteredMessages.addAll(allMessages);
        } else {
            CustomTab selectedTab = null;
            for (CustomTab t : allTabs) {
                if (t.getName().equalsIgnoreCase(category)) {
                    selectedTab = t;
                    break;
                }
            }

            if (selectedTab != null && selectedTab.getRule() != null && !selectedTab.getRule().trim().isEmpty()) {
                String[] rules = selectedTab.getRule().toLowerCase(Locale.ROOT).split(",");
                for (Message msg : allMessages) {
                    for (String r : rules) {
                        if (msg.getBody().toLowerCase(Locale.ROOT).contains(r.trim()) ||
                                msg.getTitle().toLowerCase(Locale.ROOT).contains(r.trim())) {
                            filteredMessages.add(msg);
                            break;
                        }
                    }
                }
            } else {
                for (Message msg : allMessages) {
                    if (msg.getCategory().equalsIgnoreCase(category)) {
                        filteredMessages.add(msg);
                    }
                }
            }
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<CustomTab> newTabs = TabsManager.loadTabs(this);
        if (!newTabs.equals(allTabs)) {
            allTabs = newTabs;
            setupTabs();
        }
    }
}
