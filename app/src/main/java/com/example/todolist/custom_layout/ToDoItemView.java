package com.example.todolist.custom_layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.example.todolist.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ToDoItemView extends CardView {

    Unbinder bind;

    @BindView(R.id.card_title)
    TextView cardTitle;
    @BindView(R.id.card_date)
    TextView cardDate;
    @BindView(R.id.card_description)
    TextView cardDescription;


    private void init(View view) {
        bind = ButterKnife.bind(this, view);
    }

    public ToDoItemView(@NonNull Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.to_do_item_view, this, true);
        init(view);
    }

    public ToDoItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.to_do_item_view, this, true);
        init(view);
    }

    public ToDoItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.to_do_item_view, this, true);
        init(view);
    }


    public void setVisibilityCardDate(int visibility) {
        cardDate.setVisibility(visibility);
    }

    public void setcardDate(String date) {
        cardDate.setText(date);
    }

    public void setCardTitle(String title) {
        cardTitle.setText(title);
    }

    public void setCardDescription(String description) {
        cardDescription.setText(description);
    }

}
