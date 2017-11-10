package com.merseyside.admin.taskmanager.presentation.view.dialog;

import android.support.v7.app.AppCompatDialogFragment;

import com.merseyside.admin.taskmanager.presentation.di.HasComponent;
import com.merseyside.admin.taskmanager.presentation.di.components.ActivityComponent;
import com.merseyside.admin.taskmanager.presentation.di.modules.ActivityModule;

/**
 * Created by ivan_ on 03.11.2017.
 */

public abstract class BaseDialog extends AppCompatDialogFragment {

    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<ActivityComponent> componentType) {
        return (C) componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }

    protected abstract void initializeInjector();

    protected ActivityModule getActivityModule() {
        return new ActivityModule(getActivity());
    }
}
