package com.merseyside.admin.taskmanager.presentation.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.merseyside.admin.taskmanager.TaskApplication;
import com.merseyside.admin.taskmanager.presentation.di.components.ActivityComponent;
import com.merseyside.admin.taskmanager.presentation.di.components.AppComponent;
import com.merseyside.admin.taskmanager.presentation.di.components.DaggerActivityComponent;
import com.merseyside.admin.taskmanager.presentation.di.components.NoteComponent;
import com.merseyside.admin.taskmanager.presentation.di.modules.ActivityModule;
import com.merseyside.admin.taskmanager.presentation.navigation.Navigator;

import javax.inject.Inject;

/**
 * Created by ivan_ on 01.11.2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    Navigator navigator;

     public ActivityComponent activityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected AppComponent getApplicationComponent() {
        return TaskApplication.get(this).getApplicationComponent();
    }

    protected void addFragment(int containerViewId, Fragment fragment) {
        final FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    protected void showMessage(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    public ActivityComponent getActivityComponent() {
        this.activityComponent = DaggerActivityComponent.builder()
                .appComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        return  activityComponent;
    }

    public abstract void initializeInjector();

}
