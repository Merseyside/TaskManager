package com.merseyside.admin.taskmanager.presentation.view.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.merseyside.admin.taskmanager.MyUtils;
import com.merseyside.admin.taskmanager.data.entity.Note;
import com.merseyside.admin.taskmanager.R;

import java.util.ArrayList;

/**
 * Created by ivan_ on 24.08.2017.
 */

public class NoteItemsAdapter extends ArrayAdapter<Note> {

    private Context context;
    private int id;
    private ArrayList<Note> items;
    private ArrayMap<Integer, Bitmap> covers;
    private Bitmap note;

    public NoteItemsAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Note> objects) {
        super(context, resource, objects);

        this.context = context;
        id = resource;
        items = objects;
        covers = new ArrayMap<>();

        note = BitmapFactory.decodeResource(context.getResources(), R.drawable.note);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(id, parent, false);
        }
        final Note o = items.get(position);

        if (o != null) {
            final TextView name = v.findViewById(R.id.title);
            final TextView text = v.findViewById(R.id.text);
            final ImageView image = v.findViewById(R.id.image);

            name.setText(o.getName());
            text.setText(o.getText());

            if (!o.getImg().equals("")) {
                MyTask task = new MyTask(image, position);
                task.execute(o);
            } else {
                image.setImageBitmap(note);
            }
        }
        return v;
    }

    public void onDestroy(){
        note = null;
        covers = null;
        clear();
    }

    @Override
    public Note getItem(int position)
    {
        return items.get(position);
    }

    class MyTask extends AsyncTask<Note, Void, Bitmap> {

        private ImageView view;
        private int position;
        public MyTask(ImageView view, int position){
            this.position = position;
            this.view = view;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(Note... notes) {
            for (Note o : notes) {
                try {
                    return MyUtils.getBitmap(o.getImg());
                } catch (RuntimeException ignored){return null;}
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageViewAnimatedChange(context, view, bitmap);
        }

        public void imageViewAnimatedChange(Context c, final ImageView v, final Bitmap new_image) {
            final Animation anim_in  = AnimationUtils.loadAnimation(c, android.R.anim.fade_in);
            v.setImageBitmap(new_image);
            covers.put(position, new_image);
            anim_in.setAnimationListener(new Animation.AnimationListener() {
                @Override public void onAnimationStart(Animation animation) {}
                @Override public void onAnimationRepeat(Animation animation) {}
                @Override public void onAnimationEnd(Animation animation) {}
            });
            v.startAnimation(anim_in);
        }
    }


}
