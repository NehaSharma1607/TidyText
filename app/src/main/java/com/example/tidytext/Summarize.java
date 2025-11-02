package com.example.tidytext;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Summarize extends AppCompatActivity {

    private LinearLayout layoutSummary;
    private SharedPreferences prefs;
    private boolean includePersonal = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summarize);

        layoutSummary = findViewById(R.id.layoutSummary);
        ImageButton btnSettings = findViewById(R.id.btnSettings);

        prefs = getSharedPreferences("TidyPrefs", MODE_PRIVATE);
        includePersonal = prefs.getBoolean("includePersonal", true);

        btnSettings.setOnClickListener(v -> showSettingsPopup(v));

        renderSummary();
    }

    private void renderSummary() {
        layoutSummary.removeAllViews();

        addSectionTitle("⚡ Action Items");
        addCard("Recharge Due", "Recharge reminder — offer ends tonight.");
        addCard("PAN–Aadhaar Link", "PAN–Aadhaar linking successful. Keep acknowledgment saved.");

        addSectionTitle("🔔 Reminders");
        addCard("Dinner with Family", "Casual dinner scheduled for this weekend.");
        addCard("Upcoming Bookings", "Your train and hotel bookings are confirmed between 5–8 Nov.");

        addSectionTitle("ℹ️ Information");
        addCard("Airtel Offer", "Best recharge plans ₹799–₹2499 for 28–280 days.");
        addCard("Exam Update", "Exam admit cards released for Dec session.");

        if (includePersonal) {
            addSectionTitle("💬 Personal Notes");
            addCard("Family Calls", "Reminder to call parents on Sunday.");
        }
    }

    private void addSectionTitle(String title) {
        TextView titleView = new TextView(this);
        titleView.setText(title);
        titleView.setTextSize(20);
        titleView.setTypeface(null, Typeface.BOLD);
        titleView.setTextColor(0xFF222222);
        titleView.setPadding(0, 30, 0, 10);
        layoutSummary.addView(titleView);
    }

    private void addCard(String headerText, String bodyText) {
        CardView card = new CardView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 0, 0, 20);
        card.setLayoutParams(params);
        card.setCardElevation(4f);
        card.setRadius(16f);
        card.setCardBackgroundColor(0xFFFFFFFF);
        card.setUseCompatPadding(true);

        LinearLayout innerLayout = new LinearLayout(this);
        innerLayout.setOrientation(LinearLayout.VERTICAL);
        innerLayout.setPadding(20, 20, 20, 20);

        TextView header = new TextView(this);
        header.setText(headerText);
        header.setTextSize(17);
        header.setTypeface(null, Typeface.BOLD);
        header.setTextColor(0xFF000000);

        TextView body = new TextView(this);
        body.setText(bodyText);
        body.setTextSize(15);
        body.setTextColor(0xFF444444);
        body.setPadding(0, 8, 0, 0);

        innerLayout.addView(header);
        innerLayout.addView(body);
        card.addView(innerLayout);
        layoutSummary.addView(card);
    }

    private void showSettingsPopup(View anchor) {
        View popupView = LayoutInflater.from(this).inflate(R.layout.popup_settings, null);
        PopupWindow popupWindow = new PopupWindow(
                popupView,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                true
        );

        Switch switchPersonal = popupView.findViewById(R.id.switchPersonal);
        switchPersonal.setChecked(includePersonal);

        switchPersonal.setOnCheckedChangeListener((buttonView, isChecked) -> {
            includePersonal = isChecked;
            prefs.edit().putBoolean("includePersonal", includePersonal).apply();
            renderSummary();
        });

        popupWindow.showAsDropDown(anchor, -30, 20);
    }
}
