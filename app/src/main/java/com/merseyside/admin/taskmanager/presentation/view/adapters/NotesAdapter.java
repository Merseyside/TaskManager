package com.merseyside.admin.taskmanager.presentation.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.merseyside.admin.taskmanager.R;
import com.merseyside.admin.taskmanager.data.entity.Note;
import com.merseyside.admin.taskmanager.presentation.ImageManager;
import com.merseyside.admin.taskmanager.presentation.di.ActivityContext;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ivan_ on 11.10.2017.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyHolder> {

    public interface OnItemClickListener {
        void onNoteItemClicked(Note note);
    }

    Context context;
    ArrayList<Note> notes;
    OnItemClickListener listener;

    @Inject
    public NotesAdapter(@ActivityContext Context context) {
        this.context = context;
    }

    public void setOnItemClicklistener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setNotesCollection(ArrayList<Note> notes) {
        validateNotesCollection(notes);
        this.notes = notes;
        this.notifyDataSetChanged();
    }

    private void validateNotesCollection(ArrayList<Note> notes) {
        if (notes == null) {
            throw new IllegalArgumentException("List cannot be null!");
        }
    }

    class MyHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.time_remaining)
        TextView time_remaining;
        @BindView(R.id.image)
        CircleImageView image;

        MyHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_card, parent, false);

        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {

        Note note = notes.get(position);

        holder.name.setText(note.getName());
        holder.date.setText(note.getText());
        ImageManager.setImageViewByPath(holder.image, note.getImg());

        holder.itemView.setOnClickListener(v -> {
            if (NotesAdapter.this.listener != null) {
                NotesAdapter.this.listener.onNoteItemClicked(note);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (notes != null) return notes.size();
        else return 0;
    }

    public ArrayList<Note> getData() {
        return notes;
    }
}
