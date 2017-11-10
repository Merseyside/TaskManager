package com.merseyside.admin.taskmanager.presentation.di.modules;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.merseyside.admin.taskmanager.data.repository.DataManager;
import com.merseyside.admin.taskmanager.presentation.di.ActivityContext;
import com.merseyside.admin.taskmanager.presentation.di.ApplicationContext;
import com.merseyside.admin.taskmanager.presentation.di.DatabaseInfo;
import com.merseyside.admin.taskmanager.presentation.navigation.Navigator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Application mApplication;

    public AppModule(Application app) {
        mApplication = app;
    }

    @Provides
    @ApplicationContext
    public Context getContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    @DatabaseInfo
    String provideDatabaseName() {
        return "notes.db";
    }

    @Provides
    @Singleton
    @DatabaseInfo
    Integer provideDatabaseVersion() {
        return 1;
    }

    @Provides
    @Singleton
    Navigator provideNavigator(@ActivityContext Context context) {
        return new Navigator(context);
    }
}
