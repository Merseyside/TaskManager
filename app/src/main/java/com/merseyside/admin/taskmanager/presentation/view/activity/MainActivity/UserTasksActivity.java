package com.merseyside.admin.taskmanager.presentation.view.activity.MainActivity;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.merseyside.admin.taskmanager.MyUtils;
import com.merseyside.admin.taskmanager.presentation.di.HasComponent;
import com.merseyside.admin.taskmanager.presentation.di.components.ActivityComponent;
import com.merseyside.admin.taskmanager.R;
import com.merseyside.admin.taskmanager.presentation.presenters.UserTasksPresenter;
import com.merseyside.admin.taskmanager.presentation.view.activity.BaseActivity;
import com.merseyside.admin.taskmanager.presentation.view.fragment.NotesFragment;

public class UserTasksActivity extends BaseActivity implements HasComponent<ActivityComponent>, MainView {

    private UserTasksPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyUtils.verifyStoragePermissions(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            addFragment(R.id.frame, new NotesFragment());
        }
    }

    @Override
    public void initializeInjector() {
        //empty
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public ActivityComponent getComponent() {
        return activityComponent;
    }

    @Override
    public void showMessage(String msg) {

    }
}
