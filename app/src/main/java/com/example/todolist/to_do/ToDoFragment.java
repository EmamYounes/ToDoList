package com.example.todolist.to_do;

import android.app.AlertDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.DateFormat;
import com.example.todolist.MySingleton;
import com.example.todolist.R;
import com.example.todolist.local_data.DatabaseHelper;
import com.example.todolist.to_do.adapter.ToDoListAdapter;
import com.example.todolist.to_do.model.ToDoModel;
import com.vivekkaushik.datepicker.DatePickerTimeline;
import com.vivekkaushik.datepicker.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

public class ToDoFragment extends Fragment implements ToDoView {


    private static final String TAG = "ToDoFragment";
    private ToDoPresenter presenter;
    private ToDoListAdapter adapter;
    private DatabaseHelper databaseHelper;
    private AlertDialog alertDialog;
    private DateFormat dateFormat;


    private String noteTitle = "";
    private String noteDescription = "";


    @BindView(R.id.datePickerTimeline)
    DatePickerTimeline datePickerTimeline;
    @BindView(R.id.recycler_view_id)
    RecyclerView recyclerView;
    @BindView(R.id.add_note_btn)
    Button addNoteBtn;
    @BindView(R.id.empty_text_container)
    LinearLayout emptyTextContainer;
    @BindView(R.id.empty_text)
    TextView emptyText;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        presenter = new ToDoPresenter(this);
        View view = inflater.inflate(R.layout.to_do_fragment,
                container, false);
        ButterKnife.bind(this, view);
        presenter.onStart();
        onDateSelected();
        Date[] dates = {Calendar.getInstance().getTime()};
        datePickerTimeline.deactivateDates(dates);
        databaseHelper = MySingleton.getInstance().getDatabaseHelper();
        setAdapterData();
        handleItemAction();
        return view;
    }

    private void handleItemAction() {

        adapter.setClickListener(view1 -> {

            int position = recyclerView.indexOfChild(view1);
            ToDoModel toDoModel = getListForDay(databaseHelper.getToDoList()).get(position);

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
            alertDialog.setTitle(getString(R.string.update));
            alertDialog.setIcon(R.drawable.ic_update);
            LinearLayout layout = new LinearLayout(getContext());
            layout.setOrientation(LinearLayout.VERTICAL);

            final EditText titleBox = new EditText(getContext());
            titleBox.setText(toDoModel.getCardTitle());
            layout.addView(titleBox);

            final EditText descriptionBox = new EditText(getContext());
            descriptionBox.setText(toDoModel.getCardDescription());
            layout.addView(descriptionBox);
            alertDialog.setView(layout);
            alertDialog.setPositiveButton(getString(R.string.update), null);
            alertDialog.setNegativeButton(getString(R.string.delete), null);
            AlertDialog dialog = alertDialog.show();
            handleDeleteAction(position, dialog);
            handleUpdateAction(toDoModel, titleBox, descriptionBox, dialog);
        });
    }

    private void handleUpdateAction(ToDoModel toDoModel, EditText titleBox, EditText descriptionBox, AlertDialog dialog) {
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
            toDoModel.setCardTitle(titleBox.getText().toString());
            toDoModel.setCardDescription(descriptionBox.getText().toString());
            databaseHelper.updateToDoModel(toDoModel);
            adapter.updateList(getListForDay(databaseHelper.getToDoList()));
            dialog.dismiss();
        });
    }

    private void handleDeleteAction(int position, AlertDialog dialog) {
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(v -> {
            databaseHelper.deleteToDoModel(databaseHelper.getToDoList().get(position));
            adapter.updateList(getListForDay(databaseHelper.getToDoList()));
            dialog.dismiss();
        });
    }

    private void onDateSelected() {
        datePickerTimeline.setOnDateSelectedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(int year, int month, int day, int dayOfWeek) {
                Log.d(TAG, "onDateSelected: " + day);
                addNoteBtn.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                emptyTextContainer.setVisibility(View.GONE);
                emptyText.setText(R.string.empty_text);
                dateFormat = new DateFormat(year, month, day);
                adapter.updateList(getListForDay(databaseHelper.getToDoList()));
            }

            @Override
            public void onDisabledDateSelected(int year, int month, int day, int dayOfWeek, boolean isDisabled) {
                Log.d(TAG, "onDisabledDateSelected: " + day + isDisabled);

            }
        });
    }

    private ArrayList<ToDoModel> getListForDay(ArrayList<ToDoModel> toDoModels) {
        return (ArrayList<ToDoModel>) Observable.fromIterable(toDoModels).filter(item ->
                item.getToDoDate().equalsIgnoreCase(dateFormat.getDate())).toList().blockingGet();
    }

    private void setAdapterData() {
        adapter = new ToDoListAdapter(databaseHelper.getToDoList());
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

        addAlertDialog(alertDialog, layout, titleBox, descriptionBox);

    }

    private void addAlertDialog(AlertDialog.Builder builder, LinearLayout layout, EditText titleBox, EditText descriptionBox) {
        builder.setView(layout);
        builder.setPositiveButton(getString(R.string.add_note), null);
        builder.setNegativeButton(getString(R.string.cancel), null);
        alertDialog = builder.show();
        negativeButtonAction(alertDialog);
        positiveButtonAction(alertDialog, titleBox, descriptionBox);
    }

    private void positiveButtonAction(AlertDialog alertDialog, EditText titleBox, EditText descriptionBox) {
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
            noteTitle = titleBox.getText().toString();
            noteDescription = descriptionBox.getText().toString();
            presenter.handlePostiveButtonAction();

        });
    }

    private void negativeButtonAction(AlertDialog alertDialog) {
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(v -> alertDialog.dismiss());
    }

    @Override
    public void dismissAlertDialog() {
        alertDialog.dismiss();
    }

    @Override
    public String getNoteTitle() {
        return noteTitle;
    }

    @Override
    public String getNoteDescription() {
        return noteDescription;
    }

    @Override
    public void makeToastMessage(int stringRes) {
        Toast.makeText(getContext(), getString(stringRes), Toast.LENGTH_LONG).show();
    }

    @Override
    public void addNoteToList(String noteTitle, String noteDescription) {
        ToDoModel toDoModel = new ToDoModel();
        toDoModel.setCardTitle(noteTitle);
        toDoModel.setCardDescription(noteDescription);
        toDoModel.setToDoDate(dateFormat.getDate());
        databaseHelper.insertToDoColumn(toDoModel);
        adapter.updateList(getListForDay(databaseHelper.getToDoList()));
    }

    @Override
    public void setInitialDate(int year, int month, int date) {
        datePickerTimeline.setInitialDate(year, month, date);
    }

    @Override
    public void showEmptyCase() {
        emptyTextContainer.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        emptyText.setText(R.string.empty_add_text);
    }

    @Override
    public void hideEmptyCase() {
        emptyTextContainer.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

}
