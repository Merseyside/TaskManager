package com.merseyside.admin.taskmanager.presentation.presenters;

import android.content.Context;
import android.os.AsyncTask;

import com.merseyside.admin.taskmanager.data.entity.Note;
import com.merseyside.admin.taskmanager.data.repository.DataManager;
import com.merseyside.admin.taskmanager.presentation.di.ApplicationContext;
import com.merseyside.admin.taskmanager.presentation.view.fragment.NotesView;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by ivan_ on 01.11.2017.
 */

public class NotesFragmentPresenter {

    Context context;
    NotesView notesView;

    DataManager dbManager;

    @Inject
    public NotesFragmentPresenter(@ApplicationContext Context context, DataManager dbManager) {
        this.context = context;
        this.dbManager = dbManager;
    }

    public void setView(NotesView view) {
        this.notesView = view;
    }

    public void getNotes() {
        MyAsyncTask asyncTask = new MyAsyncTask();
        asyncTask.execute();
    }

    private class MyAsyncTask extends AsyncTask<Void, Void, ArrayList<Note>> {
        @Override
        protected void onPostExecute(ArrayList<Note> notes) {
            super.onPostExecute(notes);
            notesView.setList(notes);
        }

        @Override
        protected ArrayList<Note> doInBackground(Void... voids) {
            return dbManager.getNotesFromDB();
        }
    }

}
