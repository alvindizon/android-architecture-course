package com.techyourchance.mvc.screens.common;

import android.content.Context;
import android.widget.Toast;

import com.techyourchance.mvc.R;

public class MessagesDisplayer {

    private final Context context;

    public MessagesDisplayer(Context context) {
        this.context = context;
    }

    public void showUseCaseError() {
        Toast.makeText(context, R.string.error_network_call_failed, Toast.LENGTH_SHORT).show();

    }
}
