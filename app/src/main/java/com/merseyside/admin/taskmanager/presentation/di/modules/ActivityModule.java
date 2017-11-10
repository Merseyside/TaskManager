package com.merseyside.admin.taskmanager.presentation.di.modules;

import android.app.Activity;
import android.content.Context;

import com.merseyside.admin.taskmanager.presentation.di.ActivityContext;
import com.merseyside.admin.taskmanager.presentation.di.DatabaseInfo;
import com.merseyside.admin.taskmanager.presentation.navigation.Navigator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ivan_ on 01.11.2017.
 */

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }
}
