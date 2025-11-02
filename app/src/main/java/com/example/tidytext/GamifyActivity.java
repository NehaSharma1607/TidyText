package com.example.tidytext;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class GamifyActivity extends AppCompatActivity {

    private ProgressBar progressInboxScore;
    private TextView tvInboxScore, tvStreak, tvTipOfDay;
    private CheckBox goalReadMessages, goalDeleteSpam, goalCategorizePersonal;
    private Button btnCleanInbox;
    private int inboxScore = 82;
    private int streakDays = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamify);

        progressInboxScore = findViewById(R.id.progressInboxScore);
        tvInboxScore = findViewById(R.id.tvInboxScore);
        tvStreak = findViewById(R.id.tvStreak);
        tvTipOfDay = findViewById(R.id.tvTipOfDay);
        btnCleanInbox = findViewById(R.id.btnCleanInbox);

        goalReadMessages = findViewById(R.id.goalReadMessages);
        goalDeleteSpam = findViewById(R.id.goalDeleteSpam);
        goalCategorizePersonal = findViewById(R.id.goalCategorizePersonal);

        updateGamifyUI();

        btnCleanInbox.setOnClickListener(v -> {
            Toast.makeText(this, "Inbox cleaned successfully! 🧹", Toast.LENGTH_SHORT).show();
            inboxScore = Math.min(inboxScore + 5, 100);
            updateGamifyUI();
        });
    }

    private void updateGamifyUI() {
        tvInboxScore.setText("Inbox Score: " + inboxScore + "/100");
        progressInboxScore.setProgress(inboxScore);
        tvStreak.setText("🔥 Streak: " + streakDays + " days clean inbox!");
        tvTipOfDay.setText(getRandomTip());
    }

    private String getRandomTip() {
        String[] tips = {
                "💡 Delete promotional messages you no longer need.",
                "🧠 Mark spam immediately to train the filter.",
                "📅 Set a weekly inbox cleanup reminder.",
                "✉️ Archive messages older than 30 days.",
                "⚡ Respond to pending messages right away!"
        };
        Random rand = new Random();
        return tips[rand.nextInt(tips.length)];
    }
}
