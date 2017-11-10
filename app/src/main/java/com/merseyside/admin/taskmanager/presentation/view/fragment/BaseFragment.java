package com.merseyside.admin.taskmanager.presentation.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.merseyside.admin.taskmanager.presentation.di.HasComponent;
import com.merseyside.admin.taskmanager.presentation.di.components.ActivityComponent;
import com.merseyside.admin.taskmanager.presentation.di.modules.ActivityModule;

/**
 * Created by ivan_ on 01.11.2017.
 */

public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(getActivity());
    }

    @SuppressWarnings("unchecked")
    protected <C> ActivityComponent getComponent(Class<ActivityComponent> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }

    protected abstract void initializeInjector();

}
