package com.example.pmobile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder> {

    List<MemoModel> memoList;

    public MemoAdapter(List<MemoModel> memoList) {
        this.memoList = memoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_memo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MemoModel memo = memoList.get(position);
        holder.txtJudul.setText(memo.getJudul());
        holder.txtIsi.setText(memo.getIsi());
    }

    @Override
    public int getItemCount() {
        return memoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtJudul, txtIsi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtJudul = itemView.findViewById(R.id.txtJudul);
            txtIsi = itemView.findViewById(R.id.txtIsi);
        }
    }
}
