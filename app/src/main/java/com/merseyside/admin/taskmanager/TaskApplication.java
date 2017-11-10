package com.merseyside.admin.taskmanager;


import android.content.Context;

import com.merseyside.admin.taskmanager.presentation.di.components.AppComponent;
import com.merseyside.admin.taskmanager.presentation.di.components.DaggerAppComponent;
import com.merseyside.admin.taskmanager.presentation.di.modules.AppModule;

public class TaskApplication extends android.app.Application {

    private static AppComponent component;
    public static AppComponent getApplicationComponent(){
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();
    }

    public static TaskApplication get(Context context) {
        return (TaskApplication) context.getApplicationContext();
    }

    private AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static void close() {
        System.exit(0);
    }
}
