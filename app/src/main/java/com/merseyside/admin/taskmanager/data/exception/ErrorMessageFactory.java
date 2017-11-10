package com.merseyside.admin.taskmanager.data.exception;

import android.content.Context;

import com.merseyside.admin.taskmanager.R;

import org.json.JSONException;

import java.io.IOException;

/**
 * Created by ivan_ on 11.10.2017.
 */

public class ErrorMessageFactory {

    public static String create(Context context, Exception exception) {

        String message =null;
        if (exception instanceof JSONException) {

        }
        return message;
    }

    public static String create(Throwable throwable) {
        return throwable.toString();
    }
}
