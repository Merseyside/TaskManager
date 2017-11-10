package com.merseyside.admin.taskmanager.presentation.presenters;

import android.content.Context;
import android.os.AsyncTask;

import com.merseyside.admin.taskmanager.TaskApplication;
import com.merseyside.admin.taskmanager.data.repository.DataManager;
import com.merseyside.admin.taskmanager.presentation.view.fragment.NotesView;
import com.merseyside.admin.taskmanager.data.entity.Note;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by ivan_ on 24.08.2017.
 */

public class UserTasksPresenter {

    private NotesView mainView;

    @Inject
    Context context;

    public UserTasksPresenter() {

    }


}
