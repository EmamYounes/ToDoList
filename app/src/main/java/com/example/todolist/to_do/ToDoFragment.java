package com.example.todolist.to_do;

import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.R;
import com.example.todolist.to_do.adapter.ToDoListAdapter;
import com.example.todolist.to_do.model.ToDoModel;
import com.vivekkaushik.datepicker.DatePickerTimeline;
import com.vivekkaushik.datepicker.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Date;

public class ToDoFragment extends Fragment implements ToDoView {


    private static final String TAG = "ToDoFragment";
    ToDoPresenter presenter;
    DatePickerTimeline datePickerTimeline;
    RecyclerView recyclerView;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        presenter = new ToDoPresenter(this);
        View view = inflater.inflate(R.layout.to_do_fragment,
                container, false);
        datePickerTimeline = view.findViewById(R.id.datePickerTimeline);
        recyclerView = view.findViewById(R.id.recycler_view_id);
        presenter.onStart();
        onDateSelected();
        Date[] dates = {Calendar.getInstance().getTime()};
        datePickerTimeline.deactivateDates(dates);
        return view;
    }

    private void onDateSelected() {

        datePickerTimeline.setOnDateSelectedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(int year, int month, int day, int dayOfWeek) {
                Log.d(TAG, "onDateSelected: " + day);
                setAdapterData();
            }

            @Override
            public void onDisabledDateSelected(int year, int month, int day, int dayOfWeek, boolean isDisabled) {
                Log.d(TAG, "onDisabledDateSelected: " + day + isDisabled);

            }
        });
    }

    private void setAdapterData() {
        ToDoModel toDoModel = new ToDoModel();
        toDoModel.setCardTitle("Title");
        toDoModel.setCardDescription("ajksfdas.bvfkasvfkasv.fas;lfhlasjfgas;lajksfdas.bvfkasvfkasv.fas;lfhlasjfgas;lajksfdas.bvfkasvfkasv.fas;lfhlasjfgas;lajksfdas.bvfkasvfkasv.fas;lfhlasjfgas;l");
        ArrayList<ToDoModel> toDoModels = new ArrayList<>();
        toDoModels.add(toDoModel);
        ToDoListAdapter adapter = new ToDoListAdapter(toDoModels);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setInitialDate(int year, int month, int date) {
        datePickerTimeline.setInitialDate(year, month, date);
    }
}
