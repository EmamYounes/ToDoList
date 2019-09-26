package com.example.todolist.old_task;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.todolist.R;

import butterknife.ButterKnife;

public class OldTaskFragment extends Fragment implements OldTaskView {

    OldTaskPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        presenter = new OldTaskPresenter(this);
        View view = inflater.inflate(R.layout.old_task_fragment,container,false);
        ButterKnife.bind(this,view);
        presenter.onStart();
        return view;
    }
}
