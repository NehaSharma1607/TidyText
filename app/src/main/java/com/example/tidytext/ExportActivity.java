package com.example.tidytext;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ExportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export);

        Button btnEmail = findViewById(R.id.btnExportEmail);
        Button btnCalendar = findViewById(R.id.btnExportCalendar);
        Button btnTrello = findViewById(R.id.btnExportTrello);

        btnEmail.setOnClickListener(v -> shareSummary("email"));
        btnCalendar.setOnClickListener(v -> shareSummary("calendar"));
        btnTrello.setOnClickListener(v -> shareSummary("trello"));
    }

    private void shareSummary(String type) {
        String summaryText = "TidyText Summary Report\n\n- 120 Personal Messages\n- 15 Spam filtered\n- 5 Bookings\n";

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "TidyText Summary Export");
        shareIntent.putExtra(Intent.EXTRA_TEXT, summaryText);

        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }
}
