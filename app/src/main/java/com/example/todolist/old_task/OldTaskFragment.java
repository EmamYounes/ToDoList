package com.example.todolist.old_task;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.R;
import com.example.todolist.old_task.adapter.OldTaskListAdapter;
import com.example.todolist.to_do.model.ToDoModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OldTaskFragment extends Fragment implements OldTaskView {

    OldTaskPresenter presenter;
    private OldTaskListAdapter adapter;

    @BindView(R.id.recycler_view_id)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        presenter = new OldTaskPresenter(this);
        View view = inflater.inflate(R.layout.old_task_fragment, container, false);
        ButterKnife.bind(this, view);
        presenter.onStart();
        return view;
    }

    @Override
    public void setAdapterData(ArrayList<ToDoModel> oldTaskList) {
        adapter = new OldTaskListAdapter(oldTaskList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }
}
