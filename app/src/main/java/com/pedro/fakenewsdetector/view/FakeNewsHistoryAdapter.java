package com.pedro.fakenewsdetector.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pedro.fakenewsdetector.R;
import com.pedro.fakenewsdetector.model.FakeNewsModel;

import java.util.ArrayList;
import java.util.List;

public class FakeNewsHistoryAdapter extends RecyclerView.Adapter<FakeNewsHistoryAdapter.FakeNewsHistoryViewHolder> {
    static class FakeNewsHistoryViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView date;

        public FakeNewsHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txt_title);
            date = itemView.findViewById(R.id.txt_date);
        }

        public void bind(FakeNewsModel fakeNewsModel) {
            title.setText(fakeNewsModel.getTitle());
            date.setText(fakeNewsModel.getCreatedAt().toString());
        }
    }

    private List<FakeNewsModel> items = new ArrayList<>();

    @NonNull
    @Override
    public FakeNewsHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_fake_news_history, parent, false);
        return new FakeNewsHistoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FakeNewsHistoryViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void refresh(List<FakeNewsModel> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}
