package com.merseyside.admin.taskmanager.presentation.view.activity.NoteEditorActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.merseyside.admin.taskmanager.MyUtils;
import com.merseyside.admin.taskmanager.R;
import com.merseyside.admin.taskmanager.TaskApplication;
import com.merseyside.admin.taskmanager.data.entity.Note;
import com.merseyside.admin.taskmanager.presentation.ImageManager;
import com.merseyside.admin.taskmanager.presentation.di.ActivityContext;
import com.merseyside.admin.taskmanager.presentation.di.components.DaggerNoteComponent;
import com.merseyside.admin.taskmanager.presentation.di.components.NoteComponent;
import com.merseyside.admin.taskmanager.presentation.di.modules.NoteModule;
import com.merseyside.admin.taskmanager.presentation.presenters.NoteEditorActivityPresenter;
import com.merseyside.admin.taskmanager.presentation.view.activity.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ivan_ on 08.11.2017.
 */

public class NoteEditorActivity extends BaseActivity implements EditorView {
    public static final int RESULT_DELETED = -1;
    public static final int RESULT_CREATED = 1;

    Note note;

    @Inject
    @ActivityContext
    Context context;

    @Inject
    NoteEditorActivityPresenter presenter;

    @BindView(R.id.change_tw)
    TextView change;

    @BindView(R.id.create_tw)
    TextView create;

    @BindView(R.id.title)
    EditText title;

    @BindView(R.id.text)
    EditText text;

    @BindView(R.id.image)
    ImageView image;

    boolean isCreating;
    String image_url;

    NoteComponent noteComponent;

    Menu menu;

    @Override
    public void initializeInjector() {
        this.noteComponent = DaggerNoteComponent.builder()
                .appComponent(TaskApplication.getApplicationComponent())
                .activityModule(getActivityModule())
                .noteModule(getNoteModule())
                .build();
    }

    private NoteModule getNoteModule() {
        return new NoteModule();
    }

    protected void setStatusBarTranslucent(boolean makeTranslucent) {
        if (makeTranslucent) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.app_bar_editor);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle("kek");
        //setStatusBarTranslucent(true);

        initializeInjector();
        noteComponent.inject(this);

        presenter.setView(this);

        Intent intent = getIntent();
        if ((note = intent.getParcelableExtra("note")) != null) {
            fill();
        }

        isCreating = note == null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        this.menu = menu;
        getMenuInflater().inflate(R.menu.note_editor_menu, menu);
        setCreateOrChangeMode();
        return true;
    }

    private void setCreateOrChangeMode() {
        setVisibleMenuGroup(isCreating);
        if (isCreating) {
            title.setEnabled(true);
            text.setEnabled(true);
            image.setClickable(true);
        } else {
            title.setEnabled(false);
            text.setEnabled(false);
            image.setClickable(false);
        }
    }

    private void setVisibleMenuGroup(boolean isCreating) {
        if (isCreating) {
            menu.setGroupVisible(R.id.creating_group, true);
            menu.setGroupVisible(R.id.changing_group, false);
        } else {
            menu.setGroupVisible(R.id.creating_group, false);
            menu.setGroupVisible(R.id.changing_group, true);
        }
    }

    private void setImage() {
        if (!isCreating) {
            if (note.getImg().equals("")) {

                image.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.note_header));
            } else {
                ImageManager.setImageViewByPath(image, note.getImg());
            }
        } else {
            if (image_url == null || image_url.equals("")) ImageManager.setImageViewFromResource(context, image, R.drawable.note_header);
            else {
                ImageManager.setImageViewByPath(image, image_url);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {

    }

    private void fill() {
        if (note == null) return;
        image_url = note.getImg();
        String str = context.getString(R.string.changed) + note.getChange_date();
        change.setText(str);
        str = context.getString(R.string.created) + note.getCreation_date();
        create.setText(str);

        title.setText(note.getName());
        text.setText(note.getText());

        setImage();
    }

    @Override
    public void onSuccess(Note note) {
        setResult(RESULT_CREATED);
        onBackPressed();
    }

    @Override
    public void onError(String msg) {
        showMessage(image, msg);
    }

    @Override
    public void onDeleted() {
        setResult(RESULT_DELETED);
        onBackPressed();
    }

    @OnClick(R.id.image)
    void onClickImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        try {
            startActivityForResult(Intent.createChooser(intent, "Выберите картинку"), 0);

        } catch (android.content.ActivityNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void  onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode) {
            case 0: {
                try {
                    Uri uri = data.getData();
                    image_url = MyUtils.getPath(context, uri);
                    Log.d("uri", uri.getPath());
                    setImage();
                } catch (NullPointerException ignored) {}
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case (R.id.save): {
                if (isCreating) presenter.saveNote(note, title.getText().toString(), text.getText().toString(), image_url);
                break;
            }

            case (R.id.delete): {
                presenter.deleteNote(note);
                break;
            }

            case (R.id.change): {
                isCreating = true;
                setCreateOrChangeMode();
                break;
            }

            case (R.id.cancel): {
                isCreating = false;
                fill();
                setCreateOrChangeMode();
                break;
            }

            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }
}
