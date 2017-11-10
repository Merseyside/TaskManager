package com.merseyside.admin.taskmanager.presentation.presenters;

import android.content.Context;

import com.merseyside.admin.taskmanager.data.repository.DataManager;
import com.merseyside.admin.taskmanager.MyUtils;
import com.merseyside.admin.taskmanager.presentation.di.ActivityContext;
import com.merseyside.admin.taskmanager.presentation.view.activity.NoteEditorActivity.EditorView;
import com.merseyside.admin.taskmanager.R;
import com.merseyside.admin.taskmanager.data.entity.Note;

import java.util.Calendar;

import javax.inject.Inject;

/**
 * Created by ivan_ on 24.08.2017.
 */

public class NoteEditorActivityPresenter {

    private EditorView dialogView;
    private DataManager dataManager;

    Context context;

    @Inject
    public NoteEditorActivityPresenter(@ActivityContext Context context, DataManager dataManager) {
        this.context = context;
        this.dataManager = dataManager;
    }

    public void setView(EditorView dialogView) {
        this.dialogView = dialogView;
    }

    public void saveNote(Note note, String name, String text, String image) {
        if (isValid(name, text)) {
            note = updateNote(note, name, text, image);
            if (dataManager.saveToDB(note)) dialogView.onSuccess(note);
            else dialogView.onError(context.getString(R.string.db_error));
        } else {
            dialogView.onError(context.getString(R.string.fill_error));
        }
    }

    public void deleteNote(Note note) {
        if (dataManager.deleteFromDB(note)) dialogView.onDeleted();
        else dialogView.onError(context.getString(R.string.deleting_error));
    }

    private boolean isValid(String name, String text) {
        return !name.equals("") && !text.equals("");

    }

    private Note updateNote(Note note, String name, String text, String image) {
        Calendar calendar = Calendar.getInstance();
        int day_of_year = calendar.get(Calendar.DAY_OF_YEAR);
        String today = MyUtils.getDaysToDate(context, day_of_year);
        if (note == null) note = new Note("", name, text, image, today, today);
        else {
            note.setName(name);
            note.setText(text);
            note.setImg(image);
            note.setChange_date(today);
        }
        return note;

    }
}
