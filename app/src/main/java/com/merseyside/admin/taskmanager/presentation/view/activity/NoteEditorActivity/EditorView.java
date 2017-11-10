package com.merseyside.admin.taskmanager.presentation.view.activity.NoteEditorActivity;

import com.merseyside.admin.taskmanager.data.entity.Note;

/**
 * Created by ivan_ on 24.08.2017.
 */

public interface EditorView {
    void onSuccess(Note note);
    void onError(String msg);
    void onDeleted();
}
