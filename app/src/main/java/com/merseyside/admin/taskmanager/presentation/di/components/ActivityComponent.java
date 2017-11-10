package com.merseyside.admin.taskmanager.presentation.di.components;

import android.app.Activity;
import android.content.Context;

import com.merseyside.admin.taskmanager.data.repository.DataManager;
import com.merseyside.admin.taskmanager.presentation.di.ActivityContext;
import com.merseyside.admin.taskmanager.presentation.di.PerActivity;
import com.merseyside.admin.taskmanager.presentation.di.modules.ActivityModule;
import com.merseyside.admin.taskmanager.presentation.navigation.Navigator;
import com.merseyside.admin.taskmanager.presentation.view.activity.BaseActivity;

import dagger.Component;

/**
 * Created by ivan_ on 01.11.2017.
 */

@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {
    void inject(BaseActivity activity);

    @ActivityContext
    Context getContext();

    Activity getActivity();

    Navigator getNavigator();
}
