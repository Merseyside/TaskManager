package com.merseyside.admin.taskmanager.presentation.navigation;

import android.content.Context;

import com.merseyside.admin.taskmanager.TaskApplication;
import com.merseyside.admin.taskmanager.presentation.di.ActivityContext;

import javax.inject.Inject;

/**
 * Created by ivan_ on 01.11.2017.
 */

public class Navigator {
    private Context mContext;

    @Inject
    public Navigator(@ActivityContext Context context) {
        mContext = context;
    }

    public void exit() {
        TaskApplication.close();
    }
}
