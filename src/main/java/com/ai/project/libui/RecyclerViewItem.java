package com.ai.project.libui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class RecyclerViewItem<Data> extends LinearLayout {
    protected ItemListener listener;
    protected Context context;
    protected Data data;
    protected AiAlert alert;

    public RecyclerViewItem(Context context) {
        super(context);
        initialize(context);
    }

    public RecyclerViewItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public RecyclerViewItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    private void initialize(Context context) {
        this.context = context;
        if (context instanceof AiActivity) {
            alert = new AiAlert(context);
        }
    }

    public void setItemListener(ItemListener itemListener) {
        this.listener = itemListener;
        initOnClick();
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void showErrorDialog(Exception e) {
        alert.showErrorDialog(e);
    }

    public AiActivity getActivity() {
        return (AiActivity) context;
    }

    private void initOnClick() {
        setOnClickListener(v -> listener.onRecyclerViewItemClicked(getVerticalScrollbarPosition(), v, data));
    }

    public interface ItemListener<Data> {
        void onRecyclerViewItemClicked(int position, View view, Data data);
    }
}
