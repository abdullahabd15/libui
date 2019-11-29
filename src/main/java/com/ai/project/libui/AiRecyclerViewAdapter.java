package com.ai.project.libui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AiRecyclerViewAdapter<Data> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater layoutInflater;
    private AiActivity activity;
    private List<Data> dataList;
    private RecyclerViewItem.ItemListener itemListener;
    private int layoutResource;

    public AiRecyclerViewAdapter(AiActivity activity, int layoutResource, List<Data> items) {
        this.layoutInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.dataList = items;
        this.layoutResource = layoutResource;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerViewItem<Data> recyclerViewItem = (RecyclerViewItem<Data>) layoutInflater.inflate(this.layoutResource, parent, false);
        return new AiViewHolder(recyclerViewItem);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AiViewHolder viewHolder = (AiViewHolder) holder;
        viewHolder.setData(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public void setRecyclerListener(RecyclerViewItem.ItemListener listener) {
        itemListener = listener;
    }

    public class AiViewHolder extends RecyclerView.ViewHolder {
        private RecyclerViewItem<Data> recyclerViewItem;

        public AiViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerViewItem = (RecyclerViewItem<Data>) itemView;
            if (itemListener != null) {
                recyclerViewItem.setItemListener((position, view, o) -> itemListener.onRecyclerViewItemClicked(getAdapterPosition(), view, o));
            }
        }

        public void setData(Data data) {
            recyclerViewItem.setData(data);
        }
    }
}
