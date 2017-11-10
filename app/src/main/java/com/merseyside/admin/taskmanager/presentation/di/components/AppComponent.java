package com.merseyside.admin.taskmanager.presentation.di.components;

import android.app.Application;
import android.content.Context;

import com.merseyside.admin.taskmanager.data.repository.DBHelper;
import com.merseyside.admin.taskmanager.data.repository.DataManager;
import com.merseyside.admin.taskmanager.presentation.di.ApplicationContext;
import com.merseyside.admin.taskmanager.presentation.di.modules.AppModule;
import com.merseyside.admin.taskmanager.presentation.presenters.NoteEditorActivityPresenter;

import javax.inject.Singleton;

import dagger.Component;


@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {
    void inject(NoteEditorActivityPresenter presenter);
    void inject(DataManager manager);

    @ApplicationContext
    Context getContext();

    Application getApplication();

    DataManager getDataManager();

    DBHelper getDBHelper();
}
