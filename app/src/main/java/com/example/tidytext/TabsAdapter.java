package com.example.tidytext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TabsAdapter extends RecyclerView.Adapter<TabsAdapter.ViewHolder> {

    private final Context context;
    private final List<CustomTab> tabs;

    public TabsAdapter(Context context, List<CustomTab> tabs) {
        this.context = context;
        this.tabs = tabs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tab, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CustomTab tab = tabs.get(position);
        holder.name.setText(tab.getName());
        holder.rule.setText(tab.getRule());

        // Hide delete for default tabs
        if (tab.isDefault()) {
            holder.delete.setVisibility(View.GONE);
        } else {
            holder.delete.setVisibility(View.VISIBLE);
            holder.delete.setOnClickListener(v -> {
                tabs.remove(position);
                TabsManager.saveTabs(context, tabs);
                notifyDataSetChanged();
            });
        }

        // Save rule changes
        holder.rule.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                tab.setRule(holder.rule.getText().toString());
                TabsManager.saveTabs(context, tabs);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tabs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        EditText rule;
        ImageButton delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvTabName);
            rule = itemView.findViewById(R.id.etRule);
            delete = itemView.findViewById(R.id.btnDeleteTab);
        }
    }
}
