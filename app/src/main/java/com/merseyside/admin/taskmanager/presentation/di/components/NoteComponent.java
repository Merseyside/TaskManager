package com.merseyside.admin.taskmanager.presentation.di.components;

import com.merseyside.admin.taskmanager.presentation.di.PerActivity;
import com.merseyside.admin.taskmanager.presentation.di.modules.ActivityModule;
import com.merseyside.admin.taskmanager.presentation.di.modules.NoteModule;
import com.merseyside.admin.taskmanager.presentation.view.activity.NoteEditorActivity.NoteEditorActivity;
import com.merseyside.admin.taskmanager.presentation.view.fragment.NotesFragment;

import dagger.Component;

/**
 * Created by ivan_ on 01.11.2017.
 */

@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, NoteModule.class})
@PerActivity
public interface NoteComponent {
    void inject(NotesFragment notesFragment);
    void inject(NoteEditorActivity noteDialog);
}
