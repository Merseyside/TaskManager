package com.merseyside.admin.taskmanager.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ivan_ on 24.08.2017.
 */

public class Note implements Parcelable{
    private String id, name, text, img, creation_date, change_date;

    protected Note(Parcel in) {
        id = in.readString();
        name = in.readString();
        text = in.readString();
        img = in.readString();
        creation_date = in.readString();
        change_date = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {
        if (img == null) return "";
        else return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public String getChange_date() {
        return change_date;
    }

    public void setChange_date(String change_date) {
        this.change_date = change_date;
    }

    public Note(String id, String name, String text, String img, String creation_date, String change_date) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.img = img;
        this.creation_date = creation_date;
        this.change_date = change_date;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(text);
        parcel.writeString(img);
        parcel.writeString(creation_date);
        parcel.writeString(change_date);
    }
}
