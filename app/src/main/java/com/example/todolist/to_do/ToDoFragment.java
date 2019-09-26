package com.example.todolist.to_do;

import android.app.AlertDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.MySingleton;
import com.example.todolist.R;
import com.example.todolist.local_data.DatabaseHelper;
import com.example.todolist.to_do.adapter.ToDoListAdapter;
import com.example.todolist.to_do.model.ToDoModel;
import com.vivekkaushik.datepicker.DatePickerTimeline;
import com.vivekkaushik.datepicker.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ToDoFragment extends Fragment implements ToDoView {


    private static final String TAG = "ToDoFragment";
    private ToDoPresenter presenter;
    private DatePickerTimeline datePickerTimeline;
    private RecyclerView recyclerView;
    private ToDoListAdapter adapter;
    private ArrayList<ToDoModel> toDoModels;
    DatabaseHelper databaseHelper;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        presenter = new ToDoPresenter(this);
        View view = inflater.inflate(R.layout.to_do_fragment,
                container, false);
        ButterKnife.bind(this, view);
        datePickerTimeline = view.findViewById(R.id.datePickerTimeline);
        recyclerView = view.findViewById(R.id.recycler_view_id);
        presenter.onStart();
        onDateSelected();
        Date[] dates = {Calendar.getInstance().getTime()};
        datePickerTimeline.deactivateDates(dates);
        toDoModels = new ArrayList<>();
        databaseHelper = MySingleton.getInstance().getDatabaseHelper();
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
        toDoModels = databaseHelper.getToDoList();
        adapter = new ToDoListAdapter(toDoModels);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.add_note_btn)
    public void showAddDialog() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle(getString(R.string.add_note));
        alertDialog.setIcon(R.drawable.ic_add);
        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText titleBox = new EditText(getContext());
        titleBox.setHint("Title");
        layout.addView(titleBox);

        final EditText descriptionBox = new EditText(getContext());
        descriptionBox.setHint("Description");
        layout.addView(descriptionBox);

        alertDialog.setView(layout);
        negativeButtonAction(alertDialog);
        alertDialog.setPositiveButton(getString(R.string.add_note), (dialogInterface, i) -> {
            String noteTitle = titleBox.getText().toString();
            String noteDescription = descriptionBox.getText().toString();
            if (noteDescription.isEmpty()) {
                Toast.makeText(getContext(), getString(R.string.add_note_warning), Toast.LENGTH_LONG).show();
            } else if (noteTitle.isEmpty()) {
                Toast.makeText(getContext(), getString(R.string.add_note_title_warning), Toast.LENGTH_LONG).show();
            } else {
                ToDoModel toDoModel = new ToDoModel();
                toDoModel.setCardTitle(noteTitle);
                toDoModel.setCardDescription(noteDescription);
                toDoModels.add(toDoModel);
                databaseHelper.insertToDoColumn(toDoModel);
                adapter.updateList(toDoModels);

            }
        });
        alertDialog.create();
        alertDialog.show();

    }

    private void negativeButtonAction(AlertDialog.Builder alertDialog) {
        alertDialog.setNegativeButton(getString(R.string.cancel), (dialogInterface, i) -> dialogInterface.dismiss());
    }

    @Override
    public void setInitialDate(int year, int month, int date) {
        datePickerTimeline.setInitialDate(year, month, date);
    }
}
