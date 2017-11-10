package com.merseyside.admin.taskmanager.presentation.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.merseyside.admin.taskmanager.R;
import com.merseyside.admin.taskmanager.TaskApplication;
import com.merseyside.admin.taskmanager.data.entity.Note;
import com.merseyside.admin.taskmanager.presentation.di.ActivityContext;
import com.merseyside.admin.taskmanager.presentation.di.components.DaggerNoteComponent;
import com.merseyside.admin.taskmanager.presentation.di.components.NoteComponent;
import com.merseyside.admin.taskmanager.presentation.di.modules.NoteModule;
import com.merseyside.admin.taskmanager.presentation.presenters.NotesFragmentPresenter;
import com.merseyside.admin.taskmanager.presentation.view.activity.MainActivity.MainView;
import com.merseyside.admin.taskmanager.presentation.view.activity.NoteEditorActivity.NoteEditorActivity;
import com.merseyside.admin.taskmanager.presentation.view.adapters.NotesAdapter;


import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ivan_ on 01.11.2017.
 */

public class NotesFragment extends BaseFragment implements NotesView, NotesView.OnDialogFinishedListener {

    int REQUEST_CODE = 1;

    @BindView(R.id.note_add_button)
    FloatingActionButton add_button;

    @BindView(R.id.note_recycler)
    RecyclerView listWidget;

    @Inject
    NotesFragmentPresenter presenter;

    @Inject
    NotesAdapter adapter;

    @Inject
    @ActivityContext
    Context context;

    MainView mainView;

    NoteComponent noteComponent;


    private NoteModule getNoteModule() {
        return new NoteModule();
    }

    @Override
    protected void initializeInjector() {
        this.noteComponent = DaggerNoteComponent.builder()
                .appComponent(TaskApplication.getApplicationComponent())
                .activityModule(getActivityModule())
                .noteModule(getNoteModule())
                .build();
    }

    private void setupNotesWidget() {

        RecyclerView.LayoutManager recyclerViewLayoutManager = new LinearLayoutManager(context.getApplicationContext());
        listWidget.setLayoutManager(recyclerViewLayoutManager);
        listWidget.setHasFixedSize(true);
        listWidget.setItemViewCacheSize(10);
        listWidget.setDrawingCacheEnabled(true);
        listWidget.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
    }

    @Override
    public void setList(ArrayList<Note> notes) {
        adapter.setNotesCollection(notes);
        adapter.setOnItemClicklistener(onItemClickListener);
        LinearLayoutManager verticalLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        listWidget.setLayoutManager(verticalLayout);
        listWidget.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity)context;
        try {
            mainView = (MainView) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement mainView");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeInjector();
        noteComponent.inject(this);
        presenter.setView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.notes_view, null);
        ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupNotesWidget();
        if (savedInstanceState == null) {
            presenter.getNotes();
        } else {
            setList(savedInstanceState.getParcelableArrayList("list"));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (adapter != null && adapter.getData() != null) outState.putParcelableArrayList("list", adapter.getData());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void noteDeleted() {
        showToastMessage(getString(R.string.note_removed));
        presenter.getNotes();
    }

    @Override
    public void errorOccured(String msg) {
        mainView.showMessage(msg);
    }

    @Override
    public void noteSaved() {
        showToastMessage(getString(R.string.success));
        presenter.getNotes();
    }

    @OnClick (R.id.note_add_button)
    void onAddButtonClicked() {
        openNote(null);
    }

    private NotesAdapter.OnItemClickListener onItemClickListener =
            this::openNote;

    private void openNote(Note note) {
        Intent intent = new Intent(getActivity(), NoteEditorActivity.class);
        intent.putExtra("note", note);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_CODE){
            if (resultCode == NoteEditorActivity.RESULT_CREATED) {
                showToastMessage(getString(R.string.success));
                presenter.getNotes();
            } else if (resultCode == NoteEditorActivity.RESULT_DELETED) {
                showToastMessage(getString(R.string.note_removed));
                presenter.getNotes();
            }
        }
    }
}
