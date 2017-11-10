package com.merseyside.admin.taskmanager.presentation.di.modules;

import android.content.Context;

import com.merseyside.admin.taskmanager.data.repository.DataManager;
import com.merseyside.admin.taskmanager.presentation.di.ActivityContext;
import com.merseyside.admin.taskmanager.presentation.presenters.NotesFragmentPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ivan_ on 01.11.2017.
 */

@Module
public class NoteModule {

    @Provides
    NotesFragmentPresenter provideNotesFragmentPresenter(@ActivityContext Context context, DataManager manager) {
        return new NotesFragmentPresenter(context, manager);
    }
}
