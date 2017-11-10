package com.merseyside.admin.taskmanager.presentation.view.fragment;

import com.merseyside.admin.taskmanager.data.entity.Note;

import java.util.ArrayList;

/**
 * Created by ivan_ on 24.08.2017.
 */

public interface NotesView {
    void setList(ArrayList<Note> notes);

    interface OnDialogFinishedListener{

        void noteDeleted();

        void errorOccured(String msg);

        void noteSaved();
    }
}
