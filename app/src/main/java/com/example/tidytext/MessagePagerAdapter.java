package com.example.tidytext;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessagePagerAdapter extends FragmentStateAdapter {

    private Map<String, List<Message>> categorizedMessages;
    private String[] categories;

    public MessagePagerAdapter(@NonNull FragmentActivity fragmentActivity,
                               Map<String, List<Message>> categorizedMessages) {
        super(fragmentActivity);
        this.categorizedMessages = categorizedMessages;
        this.categories = categorizedMessages.keySet().toArray(new String[0]);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return MessageFragment.newInstance(categorizedMessages.get(categories[position]));
    }

    @Override
    public int getItemCount() {
        return categories.length;
    }

    public String getPageTitle(int position) {
        return categories[position];
    }
}
