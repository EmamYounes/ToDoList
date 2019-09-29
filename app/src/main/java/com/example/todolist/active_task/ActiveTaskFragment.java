package com.example.todolist.active_task;

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
import com.example.todolist.active_task.adapter.ActiveTaskAdapter;
import com.example.todolist.to_do.model.ToDoModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActiveTaskFragment extends Fragment implements ActiveTaskView {

    private ActiveTaskPresenter presenter;
    private ActiveTaskAdapter adapter;

    @BindView(R.id.recycler_view_id)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        presenter = new ActiveTaskPresenter(this);
        View view = inflater.inflate(R.layout.active_task_fragment, container, false);
        ButterKnife.bind(this, view);
        presenter.onStart();
        return view;
    }

    @Override
    public void setAdapterData(ArrayList<ToDoModel> oldTaskList) {
        adapter = new ActiveTaskAdapter(oldTaskList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }
}
