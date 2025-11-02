package com.example.tidytext;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.*;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.*;

public class InsightsActivity extends AppCompatActivity {

    private PieChart chartDaily, chartWeekly, chartMonthly,pieChartInsights;

    private Spinner spinnerAccounts;
    private Button btnSetLimits;

    private float dailyLimit = 1000, weeklyLimit = 5000, monthlyLimit = 20000;
    private String selectedAccount = "XXXX1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insights);

        chartDaily = findViewById(R.id.chartDaily);
        chartWeekly = findViewById(R.id.chartWeekly);
        chartMonthly = findViewById(R.id.chartMonthly);
        spinnerAccounts = findViewById(R.id.spinnerAccounts);
        btnSetLimits = findViewById(R.id.btnLimits);
        pieChartInsights = findViewById(R.id.pieChartInsights);
        TextView tvMostFrequentSender = findViewById(R.id.tvTopSender);
        TextView tvSpamVolume = findViewById(R.id.tvSpamTrend);

        setupPieChart();
        tvMostFrequentSender.setText("Most Frequent Sender: Airtel");
        tvSpamVolume.setText("Spam Trend: Decreasing 📉");

        // Dummy account data
        List<String> accounts = Arrays.asList("XXXX1234", "YYYY9876", "ZZZZ1111");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, accounts);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAccounts.setAdapter(adapter);

        spinnerAccounts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedAccount = accounts.get(position);
                loadTransactionData();
            }
            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });

        btnSetLimits.setOnClickListener(v -> showLimitDialog());
    }
    private void setupPieChart() {
        List<PieEntry> entries = new ArrayList<>();

        // Example data (replace later with actual message stats)
        entries.add(new PieEntry(40f, "Personal"));
        entries.add(new PieEntry(30f, "Govt"));
        entries.add(new PieEntry(20f, "Bookings"));
        entries.add(new PieEntry(10f, "Recharge"));
        entries.add(new PieEntry(5f, "Spam"));

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setValueTextSize(12f);
        dataSet.setValueTextColor(Color.WHITE);

        PieData pieData = new PieData(dataSet);
        pieChartInsights.setData(pieData);

        // Customize chart look
        pieChartInsights.getDescription().setEnabled(false);
        pieChartInsights.setUsePercentValues(true);
        pieChartInsights.setDrawEntryLabels(false);
        pieChartInsights.setHoleColor(Color.TRANSPARENT);
        pieChartInsights.setHoleRadius(45f);
        pieChartInsights.setTransparentCircleRadius(50f);
        pieChartInsights.animateY(1000, Easing.EaseInOutQuad);
        pieChartInsights.invalidate(); // refresh
    }
    private void loadTransactionData() {
        // Replace this with your actual parsed transaction totals later
        Random r = new Random();
        float spentDaily = r.nextInt(800);
        float spentWeekly = r.nextInt(4000);
        float spentMonthly = r.nextInt(15000);

        setupPieChart(chartDaily, spentDaily, dailyLimit);
        setupPieChart(chartWeekly, spentWeekly, weeklyLimit);
        setupPieChart(chartMonthly, spentMonthly, monthlyLimit);
    }

    private void setupPieChart(PieChart chart, float spent, float limit) {
        float left = Math.max(limit - spent, 0);
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(spent, "Spent"));
        entries.add(new PieEntry(left, "Left"));

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(new int[]{android.graphics.Color.RED, android.graphics.Color.GREEN});
        PieData data = new PieData(dataSet);

        chart.setData(data);
        chart.getDescription().setEnabled(false);
        chart.setCenterText("₹" + spent + " spent");
        chart.setCenterTextSize(12f);
        chart.animateY(1000);
        chart.invalidate();
    }

    private void showLimitDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_set_limits, null);
        EditText etDaily = dialogView.findViewById(R.id.etDailyLimit);
        EditText etWeekly = dialogView.findViewById(R.id.etWeeklyLimit);
        EditText etMonthly = dialogView.findViewById(R.id.etMonthlyLimit);

        etDaily.setText(String.valueOf((int) dailyLimit));
        etWeekly.setText(String.valueOf((int) weeklyLimit));
        etMonthly.setText(String.valueOf((int) monthlyLimit));

        new AlertDialog.Builder(this)
                .setTitle("Set Spending Limits")
                .setView(dialogView)
                .setPositiveButton("Save", (dialog, which) -> {
                    dailyLimit = parseOrDefault(etDaily.getText().toString(), dailyLimit);
                    weeklyLimit = parseOrDefault(etWeekly.getText().toString(), weeklyLimit);
                    monthlyLimit = parseOrDefault(etMonthly.getText().toString(), monthlyLimit);
                    loadTransactionData();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private float parseOrDefault(String text, float defaultVal) {
        try { return Float.parseFloat(text); }
        catch (Exception e) { return defaultVal; }
    }
}
